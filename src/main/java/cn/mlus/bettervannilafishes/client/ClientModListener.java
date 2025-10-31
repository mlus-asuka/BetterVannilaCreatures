package cn.mlus.bettervannilafishes.client;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.client.model.entity.CodModel;
import cn.mlus.bettervannilafishes.client.model.entity.PufferfishModel;
import cn.mlus.bettervannilafishes.client.model.entity.SalmonModel;
import cn.mlus.bettervannilafishes.client.render.entity.BvcRenderer;
import cn.mlus.bettervannilafishes.client.render.entity.BvcSpecimenRenderer;
import cn.mlus.bettervannilafishes.init.BvcBlockEntities;
import cn.mlus.bettervannilafishes.init.BvcEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterVannilaFishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value =Dist.CLIENT)
public final class ClientModListener {
	@SubscribeEvent
	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BvcEntities.BVC_HADDOCK_COD.get(), context -> new BvcRenderer<>(context, new CodModel()));
		event.registerEntityRenderer(BvcEntities.BVC_ATLANTIC_COD.get(), context -> new BvcRenderer<>(context, new CodModel()));
		event.registerEntityRenderer(BvcEntities.BVC_PACIFIC_COD.get(), context -> new BvcRenderer<>(context, new CodModel()));
		event.registerEntityRenderer(BvcEntities.BVC_SALMON_MALE.get(), context -> new BvcRenderer<>(context, new SalmonModel()));
		event.registerEntityRenderer(BvcEntities.BVC_SALMON_FEMALE.get(), context -> new BvcRenderer<>(context, new SalmonModel()));
		event.registerEntityRenderer(BvcEntities.BVC_SALMON_PACIFIC.get(), context -> new BvcRenderer<>(context, new SalmonModel()));
		event.registerEntityRenderer(BvcEntities.YELLOW_FIN_PUFFER.get(), context -> new BvcRenderer<>(context, new PufferfishModel()));
		event.registerEntityRenderer(BvcEntities.OBSCURE_PUFFER.get(), context -> new BvcRenderer<>(context, new PufferfishModel()));
		event.registerEntityRenderer(BvcEntities.NAUTILUS.get(), BvcRenderer::new);

		event.registerBlockEntityRenderer(BvcBlockEntities.ATLANTIC_COD_SPECIMEN.get(), (context) -> new BvcSpecimenRenderer());
		event.registerBlockEntityRenderer(BvcBlockEntities.PACIFIC_COD_SPECIMEN.get(), (context) -> new BvcSpecimenRenderer());
		event.registerBlockEntityRenderer(BvcBlockEntities.HADDOCK_COD_SPECIMEN.get(), (context) -> new BvcSpecimenRenderer());
		event.registerBlockEntityRenderer(BvcBlockEntities.PACIFIC_SALMON_SPECIMEN.get(), (context) -> new BvcSpecimenRenderer());
		event.registerBlockEntityRenderer(BvcBlockEntities.MALE_SALMON_SPECIMEN.get(), (context) -> new BvcSpecimenRenderer());
		event.registerBlockEntityRenderer(BvcBlockEntities.FEMALE_SALMON_SPECIMEN.get(), (context) -> new BvcSpecimenRenderer());
	}

}