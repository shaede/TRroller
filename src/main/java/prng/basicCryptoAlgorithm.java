package prng;

import java.security.SecureRandom;

public class basicCryptoAlgorithm extends randomnessAlgorithm{
    private final SecureRandom srng;
    public basicCryptoAlgorithm()
    {
        //idD = distros.normal;
        idA = algorithms.secureBasic;
        srng = new SecureRandom();
    }

    @Override
    public int nextNumber(int dice) {
        int num=20;
        if (super.idD==null)
            super.idD = distros.normal;
        switch (super.idD)
        {
            case normal:
                num = (int) ((srng.nextGaussian() * 100) % (dice - 1) + 1);
            case beta:
                num = 20;
            default:
                num =srng.nextInt(dice-1) + 1;

        }
        return num;
    }

    @Override
    public void reseed() {
        srng.setSeed(srng.generateSeed(1024));

    }

    @Override
    public void reseed(long seed) {
        srng.setSeed(seed);
    }

}
