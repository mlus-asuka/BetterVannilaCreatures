package cn.mlus.bettervannilafishes.client.animator;

import net.minecraft.util.Mth;

public class LerpedFloat
{
    protected float current;
    protected float previous;

    public LerpedFloat()
    {
        current = previous = 0;
    }

    public LerpedFloat(float start)
    {
        current = previous = start;
    }

    public float get(float x)
    {
        return Mth.clampedLerp(previous, current, x);
    }

    public float get()
    {
        return current;
    }

    public void set(float value)
    {
        sync();
        current = value;
    }

    public void add(float value)
    {
        sync();
        current += value;
    }

    public void sync()
    {
        previous = current;
    }

    public float getPrevious()
    {
        return previous;
    }

    public static Clamped unit()
    {
        return new Clamped(0, 1);
    }

    /**
     * Clamped实现。
     * 基本上只是确保值保持在指定的{@link Clamped#min}-{@link Clamped#max}边界内。
     */
    public static class Clamped extends LerpedFloat
    {
        private final float min;
        private final float max;

        public Clamped(float start, float min, float max)
        {
            super(Mth.clamp(start, min, max));
            this.min = min;
            this.max = max;
        }

        public Clamped(float min, float max)
        {
            this(0, min, max);
        }

        @Override
        public void set(float value)
        {
            super.set(Mth.clamp(value, min, max));
        }

        @Override
        public void add(float value)
        {
            super.add(value);
            current = Mth.clamp(current, min, max);
        }

        public float getMin()
        {
            return min;
        }

        public float getMax()
        {
            return max;
        }
    }
}
