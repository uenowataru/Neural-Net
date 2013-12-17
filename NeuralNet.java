

public class NeuralNet {
	private Perceptron[][] layers = null;
	
	/* 
	 * Constructs a NeuralNet with the given layersizes 
	 */
	public NeuralNet(int[] layersize){
		this.layers = new Perceptron[layersize.length-1][];
		for(int numlayer = 0; numlayer < this.layers.length; numlayer++){
			this.layers[numlayer] = new Perceptron[layersize[numlayer+1]];
			for(int numperceptron = 0; numperceptron < layersize[numlayer]; numperceptron++){
				this.layers[numlayer][numperceptron] = new Perceptron(layersize[numlayer]);
			}
		}
	}
	
	/* 
	 * Propagate input vector forward to calculate outputs.
	 */
	public int[][] feedForward(int[] input){
		int[][] outputs = new int[layers.length+1][];
		outputs[0] = input;
		for(int numlayer = 0; numlayer < this.layers.length; numlayer++){
			outputs[numlayer] = new int[this.layers[numlayer].length];
			for(int numperceptron = 0; numperceptron < this.layers[numlayer].length; numperceptron++){
				outputs[numlayer][numperceptron] = this.layers[numlayer][numperceptron].sigmoidActivation(outputs[numlayer]);
			}
		}
		return outputs;
	}
	
	/* 
	 * Run a single iteration of backward propagation learning algorithm. 
	 */
	public double backPropLearning(int[][][] examples, double alpha){
		double averageWeightChange = 0.0;
		int numinputs = 0;
		for(int i = 0; i < examples.length; i++){
			double[][] deltas = new double[this.layers.length][];
			int[][] output = feedForward(examples[i][0]);
			
			/* Calculate output errors for each output perceptron and keep track 
            of error sum. Add error delta values to list.*/
			deltas[deltas.length-1] = new double[output[output.length-1].length];
			for(int outindex = 0; outindex < deltas[deltas.length-1].length; outindex++){
                int error = examples[i][1][outindex]-output[output.length-1][outindex];
                int[] ini = output[output.length - 2];
                double gprime = this.layers[this.layers.length-1][outindex].sigmoidActivationDeriv(ini);
                deltas[deltas.length-1][outindex] = error*gprime;
                //averageError += 0.5*error**2;
			}
			
			/* Backpropagate through all hidden layers, calculating and storing
            the deltas for each perceptron layer. */
			for(int layer = 0; layer < this.layers.length-1; layer++){
				int layerindex = this.layers.length - layer - 2;
				Perceptron[] perceptronsj = this.layers[layerindex];
		        Perceptron[] perceptronsi = this.layers[layerindex+1];
		        double[] delta = new double[this.layers[layerindex].length];
                for(int indexj = 0; indexj < this.layers[layerindex].length; indexj++){
                    double[] deltai = deltas[0];
                    int[] inj = output[layerindex];
                    double gprime = perceptronsj[indexj].sigmoidActivationDeriv(inj);
                    double sumdeltas = 0.0;
                    for(int indexi = 0; indexi < perceptronsi.length; indexi++){
                    	sumdeltas += perceptronsi[indexi].getWeights()[indexj+1]*deltai[indexi];
                    }
                    delta[indexj] = gprime*sumdeltas;
                }
		        deltas[this.layers.length - layer - 2] = delta;
			}
			
			/* Having aggregated all deltas, update the weights of the 
            hidden and output layers accordingly. */
            for(int layer = 0; layer < this.layers.length; layer++){
                for(int perceptron = 0; perceptron < this.layers[layer].length; perceptron++){
                    numinputs+=output[layer].length+1;
                    double delta = deltas[layer][perceptron];
                    int[] inj = output[layer];
                    averageWeightChange+=this.layers[layer][perceptron].updateWeights(inj, alpha, delta);
                }
            }

		}
		return averageWeightChange/numinputs;
	}
	
	/*
	 * Train a neural net for the given input.
    Args: 
        examples:                                A list of training examples
        alpha (double): 						 the alpha to train with
        weightChangeThreshold (double):          The threshold to stop training at
        maxItr (int):                            Maximum number of iterations to run
                                                 
    		Returns
	       tuple<NeuralNet,float>
	       
	       A tuple of the trained Neural Network and the accuracy that it achieved 
	       once the weight modification reached the threshold, or the iteration 
	       exceeds the maximum iteration.
	 */
	public double buildNeuralNet(int[][][] examples, double alpha, double weightChangeThreshold, int maxItr){
	    int iteration = 0;
	    double weightMod = weightChangeThreshold + 1;
	    while(weightMod > weightChangeThreshold && iteration < maxItr){
	        weightMod = backPropLearning(examples, alpha);
	        iteration+=1;
	    }
	    return weightMod;
	}
}
