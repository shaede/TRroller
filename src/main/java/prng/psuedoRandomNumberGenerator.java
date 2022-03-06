package prng;

public class psuedoRandomNumberGenerator {
    private randomnessAlgorithm m_randomness;
    private algorithms m_currentAlgorithm;
    private basicAlgorithm b_algo;
    private basicCryptoAlgorithm bc_algo;
    private static psuedoRandomNumberGenerator prng_singleton = null;
    //private distros m_currentDistro;

    private psuedoRandomNumberGenerator ()
    {
        setM_currentAlgorithm(algorithms.basic);
    }
    public static psuedoRandomNumberGenerator getPrng(){
        if (prng_singleton == null)
            prng_singleton = new psuedoRandomNumberGenerator();
        return prng_singleton;
    }
    public algorithms getM_currentAlgorithm()
    {
        return m_currentAlgorithm;
    }
    public distros getM_currentDistro()
    {
        return m_randomness.getDistroId();
    }
    public void reseed() {m_randomness.reseed();}
    public int roll20 ()
    {
        //calculate according to the current distro
        return m_randomness.nextNumber(20);
    }
    public int roll4()
    {
        return m_randomness.nextNumber(4);
    }
    public int roll6()
    {
        return m_randomness.nextNumber(6);
    }
    public int roll8()
    {
        return m_randomness.nextNumber(8);
    }
    public int roll12()
    {
        return m_randomness.nextNumber(12);
    }
    public int roll10() {
        return m_randomness.nextNumber(10);
    }
    public int roll100() {
        return m_randomness.nextNumber(100);
    }
    public int roll30()
    {
        return m_randomness.nextNumber(30);
    }
    public char rollLetter()
    {
        return (char) (m_randomness.nextNumber(17) + 64);
    }


    public String rollHiLo()
    {
        return m_randomness.nextNumber(2) == 1 ? "Low" : "High";
    }
    public void setM_currentAlgorithm(algorithms currentAlgorithm) {

        m_currentAlgorithm = currentAlgorithm;
        switch (m_currentAlgorithm)
        {
            case basic:
                //System.out.println ("switched to basic algorithm");
                if (b_algo == null)
                    b_algo = new basicAlgorithm();
                m_randomness = b_algo;
                break;
            case secureBasic:
                //System.out.println ("switched to secure basic algorithm");
                if (bc_algo == null)
                    bc_algo = new basicCryptoAlgorithm();
                m_randomness = bc_algo;
                break;
            case blumBlumShub:
                //System.out.println ("switched to Blum Blum Shub algorithm");
                m_randomness = null; //TODO
                break;
            case fibonacci:
                //System.out.println ("switched to Fibonacci algorithm");
                m_randomness = null; //TODO
                break;
            case shiftRegister:
                //System.out.println ("switched to shift register algorithm");
                m_randomness = null; //TODO
                break;
            default:
                m_randomness = null;
                break;

        }
    }

    public void setM_currentDistro(distros currentDistro){
        m_randomness.setIdD(currentDistro);
    }

    private int uniform(int num)
    {
        int m_num=0;
        //TODO
        return m_num;
    }
    private int normal(int num)
    {
        int m_num=0;
        //TODO
        return m_num;
    }
    private int beta(int num)
    {
        int m_num=0;
        //TODO
        return m_num;
    }



}
