package cn.mlus.bettervannilacreatures.block;

import cn.mlus.bettervannilacreatures.block.be.FishSpecimenBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class FishSpecimen extends BaseEntityBlock implements GeoBlockEntity {
    public static final IntegerProperty HANGING = IntegerProperty.create("hanging", 0, 2);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public FishSpecimen(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, 0)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        int hangingState = state.getValue(HANGING);

        return switch (hangingState) {
            case 0 ->
                    Block.canSupportCenter(level, pos.below(), Direction.UP);
            case 1 -> {
                Direction facing = state.getValue(FACING);
                yield Block.canSupportCenter(level, pos.relative(facing.getOpposite()), facing);
            }
            case 2 ->
                    Block.canSupportCenter(level, pos.above(), Direction.DOWN);
            default -> false;
        };
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        int hangingState;
        Direction facing;

        if (clickedFace == Direction.DOWN) {
            hangingState = 2;
            facing = context.getHorizontalDirection();
        } else if (clickedFace == Direction.UP) {
            hangingState = 0;
            facing = context.getHorizontalDirection();
        } else {
            hangingState = 1;
            facing = clickedFace;
        }

        BlockState blockstate = this.defaultBlockState()
                .setValue(HANGING, hangingState)
                .setValue(FACING, facing);

        if (blockstate.canSurvive(level, pos)) {
            return blockstate;
        }

        if (hangingState != 0) {
            blockstate = this.defaultBlockState()
                    .setValue(HANGING, 0)
                    .setValue(FACING, context.getHorizontalDirection());
            if (blockstate.canSurvive(level, pos)) {
                return blockstate;
            }
        }

        if (hangingState != 1) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                blockstate = this.defaultBlockState()
                        .setValue(HANGING, 1)
                        .setValue(FACING, direction);
                if (blockstate.canSurvive(level, pos)) {
                    return blockstate;
                }
            }
        }

        if (hangingState != 2) {
            blockstate = this.defaultBlockState()
                    .setValue(HANGING, 2)
                    .setValue(FACING, context.getHorizontalDirection());
            if (blockstate.canSurvive(level, pos)) {
                return blockstate;
            }
        }

        return null;
    }

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FishSpecimenBlockEntity fishEntity) {
            if (stack.hasTag() && stack.getTag().contains("Scale")) {
                float scale = stack.getTag().getFloat("Scale");
                fishEntity.setScale(scale);
            } else {
                fishEntity.setScale(1.0F);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof FishSpecimenBlockEntity entity) {
                ItemStack drop = new ItemStack(state.getBlock());
                drop.getOrCreateTag().putFloat("Scale", entity.getScale());
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), drop));
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(HANGING) == 2 ? HANGING_AABB : AABB;
    }
    private static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    private static final VoxelShape HANGING_AABB = Block.box(0.0D, -20.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HANGING, FACING);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new FishSpecimenBlockEntity(blockPos, blockState);
    }
}
