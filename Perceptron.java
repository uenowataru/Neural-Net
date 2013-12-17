import java.util.Arrays;
import java.util.Random;

public class Perceptron {
	private double[] weights;

	public Perceptron(int numinputs) {
		this.weights = new double[numinputs];
		Random random = new Random();
		for(int i = 0; i < weights.length; i++){
			weights[i] = random.nextDouble();
		}
	}

	public static double sigmoid(double value) {
		return 1 / (1 + Math.exp(-value));
	}

	public static double sigmoidDeriv(double value) {
		return Math.exp(-value)
				/ ((1 + Math.exp(-value)) * (1 + Math.exp(-value)));
	}

	public int sigmoidActivation(int[] inputs) {
		double suminput = this.weights[0]; //account for the bias input
		for (int i = 0; i < inputs.length; i++) {
			suminput += inputs[i] * this.weights[i + 1];
		}
		return (int) Math.round(this.sigmoid(suminput));
	}
	
	public double sigmoidActivationDeriv(int[] inputs) {
		double suminput = this.weights[0]; //account for the bias input
		for (int i = 0; i < inputs.length; i++) {
			suminput += inputs[i] * this.weights[i + 1];
		}
		return this.sigmoidDeriv(suminput);
	}
	
	public double updateWeights(int[] inputs, double alpha, double delta){
		this.weights[0] += alpha*delta*1.0;
		double totalModification = Math.abs(alpha*delta*1.0);
		for(int i = 0; i < inputs.length; i++){
			double mod = alpha*delta*inputs[i];
			this.weights[i] += mod;
			totalModification += Math.abs(mod);
		}
		return totalModification;
	}
	
	public double[] getWeights(){
		return this.weights;
	}
}
