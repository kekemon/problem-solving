import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
	private LinkedHashMap<Character, Integer> frequencyMap;
	private LinkedHashMap<Character, String> codeMap;
	
    /**
     * Initialize global variables you create
     */
    public Huffman() {
    	frequencyMap = new LinkedHashMap <>();
    	codeMap = new LinkedHashMap <>();
    }



    /**
     * Produces the output frequency.txt
     *
     * @param input - File containing the message
     * @throws Exception
     */
    public void frequency(String input) throws Exception { 
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(input)));
    	frequencyMap.clear();
    	int cASCII;
    	while((cASCII =  bufferedReader.read()) != -1) {
    		Character character = new Character((char) cASCII);
    		
    		if (character == ' '){
    			character = new Character('S');
    		} else if (character == '\n') {
    			character = new Character('N');
    		}
    		
    		if(frequencyMap.get(character) == null) {
    			frequencyMap.put(character, 1);
    		} else {
    			frequencyMap.put(character, frequencyMap.get(character) + 1);
    		}
    	}

    	bufferedReader.close();
    	
    	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("frequency.txt")));
    	
    	for(Entry<Character, Integer> entry : frequencyMap.entrySet()){ 
    		bufferedWriter.write(entry.getKey()+" "+entry.getValue() + "\n"); 
    	}  
    	bufferedWriter.close();
    }

    

    /**
     * Produces the output codes.txt and tree.txt
     *
     * @param freqFile - File containing the frequencies
     * @throws Exception
     */
    public void buildTree(String freqFile) throws Exception {
        /** use the queue below **/
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        
        Scanner scanner = new Scanner(new File(freqFile));
        while(scanner.hasNext()) {
        	char letter = scanner.next().charAt(0);
        	int frequency = scanner.nextInt();
        	HuffmanNode huffmanNode = new HuffmanNode(letter, frequency);
        	queue.add(huffmanNode);
        }
        scanner.close();
        
        while (queue.size() > 1) {
        	HuffmanNode smallest = queue.poll();
        	HuffmanNode nextSmallest = queue.poll();
        	HuffmanNode newHuffmanNode = new HuffmanNode(smallest, nextSmallest);
        	
        	queue.add(newHuffmanNode);
		}
        
        HuffmanNode huffmanRoot = queue.poll();
        
        BufferedWriter codeWriter = new BufferedWriter(new FileWriter(new File("codes.txt")));
        BufferedWriter treeWriter = new BufferedWriter(new FileWriter(new File("tree.txt")));
        
        traverseInOrder(huffmanRoot, codeWriter, treeWriter, "");
        
    	codeWriter.close();
    	treeWriter.close();
    }


    private void traverseInOrder(HuffmanNode huffmanRoot, BufferedWriter codeWriter, BufferedWriter treeWriter, String code) throws Exception {
    	
    	if (huffmanRoot.left != null && huffmanRoot.right != null) {
    		treeWriter.write(huffmanRoot.left.getNode() + "-" + huffmanRoot.getNode() + "-" + huffmanRoot.right.getNode() + "\n");
    		traverseInOrder(huffmanRoot.left, codeWriter, treeWriter, code + huffmanRoot.left.index);
    		traverseInOrder(huffmanRoot.right, codeWriter, treeWriter, code + huffmanRoot.right.index);
    	}
    	
    	if (huffmanRoot.letter != ' ') {
    		codeWriter.write(huffmanRoot.letter + " " + code + "\n");
    	}
    }

    /**
     * Produces the output encode.bin
     *
     * @param code - File containing the bit codes
     * @param message -  File containing the message
     * @throws Exception
     */
    public void encode(String code, String message) throws Exception { 
    	  Scanner scanner = new Scanner(new File(code));
    	  codeMap.clear();
    	  while(scanner.hasNext()) {
    		  codeMap.put(scanner.next().charAt(0), scanner.next());
          }
    	  scanner.close();
    	  
    	  BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(message)));
    	  String encodedMessage = "";
    	  int cASCII;
    	  while((cASCII =  bufferedReader.read()) != -1) {
    		  Character character = new Character((char) cASCII);

    		  if (character == ' '){
    			  character = new Character('S');
    		  } else if (character == '\n') {
    			  character = new Character('N');
    		  }
    		  encodedMessage += codeMap.get(character);
    	  }
    	  bufferedReader.close();
    	  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("encode.bin")));
    	  bufferedWriter.write(encodedMessage + "\n");
    	  bufferedWriter.close();
        
    }



    /**
     * Produces the output decode.txt
     *
     * @param tree - File containing the Huffman tree
     * @param encode - - File containing the encoded message
     * @throws Exception
     */
    public void decode(String tree, String encode) throws Exception { 
    	BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(tree)));
    	HuffmanNode huffmanRootNode = createHuffmanTree(bufferedReader, null);
    	bufferedReader.close();
    	
    	bufferedReader = new BufferedReader(new FileReader(new File(encode)));
    	String textMessage = "";
    	int cASCII;
    	HuffmanNode searchStart = huffmanRootNode;
    	while((cASCII =  bufferedReader.read()) != -1) {
    		if (cASCII == '0') {
    			searchStart = searchStart.left;
    		} else {
    			searchStart = searchStart.right;
    		}
    		
    		if (searchStart.letter != ' ') {
    			if (searchStart.letter == 'N') {
    				textMessage += '\n';	
    			} else if (searchStart.letter == 'S') {
    				textMessage += ' ';
    			} else {
    				textMessage += searchStart.letter;
    			}
    			searchStart = huffmanRootNode;
    		}
    	}

    	bufferedReader.close();
    	
    	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("decode.txt")));
    	bufferedWriter.write(textMessage + "\n");
    	bufferedWriter.close();
    	
    }


    private HuffmanNode createHuffmanTree(BufferedReader bufferedReader,HuffmanNode huffmanRootNode) throws Exception {
    	String line;
    	if((line = bufferedReader.readLine()) == null) {
    		return null;
    	}
    	
    	String parts[] = line.split("-");
		HuffmanNode leftNode = createHuffmanNode(parts[0]);
		HuffmanNode rightNode = createHuffmanNode(parts[2]);
		
		if (huffmanRootNode == null) {
			huffmanRootNode = createHuffmanNode(parts[1]);
		}
		
		huffmanRootNode.left = leftNode;
		huffmanRootNode.right = rightNode;
		huffmanRootNode.right.index = 1;
		leftNode.prev = huffmanRootNode;
		rightNode.prev = huffmanRootNode;
		
		if (leftNode.letter == ' ') {
			createHuffmanTree(bufferedReader, leftNode);
		} 
		
		if (rightNode.letter == ' ') {
			createHuffmanTree(bufferedReader, rightNode);
		} 

		return huffmanRootNode;
    }
    
    private HuffmanNode createHuffmanNode(String value) {
    	int nodeValue = getNumber(value);
    	if (nodeValue == 0) {
    		return new HuffmanNode(value.charAt(0), 0);
    	} else {
    		return new HuffmanNode(' ', nodeValue);
    	}
    }
    
    private int getNumber(String strNum) {
        try {
        	Integer number = Integer.parseInt(strNum);
        	return number;
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }
    

    /**
     * Auxiliary class for Huffman
     *
     */
    class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        int index;
        char letter;
        HuffmanNode left;
        HuffmanNode right;
        HuffmanNode prev;
        

        public HuffmanNode(char letter, int frequency) {
            this.letter = letter;
            this.frequency = frequency;
            this.index = 0;
        }
        
        public HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this(' ', left.frequency + right.frequency);
            this.left = left;
            this.right = right;
            left.prev = this;
            right.prev = this;
            left.index = 0;
            right.index = 1;
        }
        
        /**
         * Uses frequency to determine the nodes order in the queue
         * Note: DO NOT MODIFY THIS FUNCTION
         *
         * @param node of type HuffmanNode
         * @return frequency of key node subtracted by frequency of node from parameter
         */
        @Override
        public int compareTo(HuffmanNode node) {
            return frequency - node.frequency;
        }
        
        public String getNode() {
        	if (letter == ' ') {
        		return frequency + "";
        	} else {
        		return letter + "";
        	}
        }

    }


}