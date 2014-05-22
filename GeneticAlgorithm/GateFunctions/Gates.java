package GeneticAlgorithm.GateFunctions; 

// Collection of functions that simulate digital logic gates
public class Gates
{
    // AND Gate
    public static boolean and(boolean input1, boolean input2)
    {
	return input1 && input2;
    }

    // NAND Gate
    public static boolean nand(boolean input1, boolean input2)
    {
	return !(input1 && input2);
    }

    // NOR Gate
    public static boolean nor(boolean input1, boolean input2)
    {
	return !(input1 || input2);
    }

    // NOT Gate
    public static boolean not(boolean input1, boolean input2)
    {
	return !input1;
    }

    // OR Gate
    public static boolean or(boolean input1, boolean input2)
    {
	return input1 || input2;
    }

    // WIRE
    public static boolean wire(boolean input1, boolean input2)
    {
	return input1;
    }

    // XNOR Gate
    public static boolean xnor(boolean input1, boolean input2)
    {
	return (input1 && input2) || (!input1 && !input2);
    }

    // XOR Gate
    public static boolean xor(boolean input1, boolean input2)
    {
	return (input1 && !input2) || (!input1 && input2);
    }
}
