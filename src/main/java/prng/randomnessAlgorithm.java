package prng;

public abstract class randomnessAlgorithm {
    protected algorithms idA;
    protected distros idD;

    //abstract methods to be implemented by inheriting classes
    public randomnessAlgorithm()
    {
        idD = distros.normal;
    }
    public abstract int nextNumber(int dice);

    public abstract void reseed();
    public abstract void reseed(long seed);
    public void setIdD(distros dis)
    {
        idD=dis;
    }

    //returns the id of the algorithm being used
    public algorithms getAlgoId()
    {
        return idA;
    }
    public distros getDistroId(){
        return idD;
    }
    //void reseed(int [] seed);

}
