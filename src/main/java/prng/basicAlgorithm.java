package prng;

import java.util.Random;

public class basicAlgorithm extends randomnessAlgorithm{
    private final Random rng;
    public basicAlgorithm ()
    {
        //System.out.println("Created basic algorithm");
        idA = algorithms.basic;
        //idD = distros.normal;
        rng = new Random();
    }
    public basicAlgorithm (long seed)
    {
        //idD = distros.uniform;
        idA = algorithms.basic;
        rng = new Random(seed);
    }


    @Override
    public int nextNumber(int dice) {
        int num=20;
        if (super.idD==null)
            super.idD = distros.normal;
        switch (super.idD)
        {
            case normal:
                num = (int) ((rng.nextGaussian() * 100) % (dice - 1) + 1);
            case beta:
                num = 20;
            case uniform:
                num =rng.nextInt(dice-1) + 1;

        }
        return num;
    }

    @Override
    public void reseed() {
        rng.setSeed(System.currentTimeMillis());
    }

    @Override
    public void reseed(long seed) {
        rng.setSeed(seed);
    }

}
