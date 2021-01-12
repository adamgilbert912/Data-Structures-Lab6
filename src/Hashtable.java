
 
 /*  Linear Probing Hash Table */ 
public class Hashtable {
	private int nElems, maxSize;       
  
   public Pair[] map = null;	// stores hash table elements
 
    // Constructor 
    public Hashtable(int capacity) {
      nElems = 0;
        maxSize = capacity;
    	map = new Pair[maxSize];
		for(int i = 0; i < maxSize ; i++)
			map[i]=null;
    }  
 
 
    // Function to check if hash table is empty 
    public boolean isEmpty() 
    {
        return getSize() == 0;
    }
    
    
    // Function to get size of hash table 
    public int getSize() 
    {
        return nElems;
    }
 

    // Function to check if hash table is full 
    public boolean isFull() 
    {
        return nElems == maxSize;
    }
 

    // Function to check if hash table contains a key
    public boolean contains(String key) 
    {
        return get(key) !=  null;
    }
 
    
    // Function to get hash-code/hash-value for a given key 
    public int hash(String key) 
    {
        return Math.abs(key.hashCode()) % maxSize;
    }    
 
    
    // Function to get value for a given key 
    public String get(String key) 
    {
    	// TODO(1)
    	// Get element value using key and linear probing
        // If the element doesn't exist, return null
        
        int index = hash(key);

        if (map[index] == null)
            return null;

        if (map[index].getKey().compareTo(key) == 0)
            return map[index].getValue();

        int curIndex = index + 1;
        Pair curPair  = map[curIndex];
        String firstKey = map[index].getKey();

        while (curPair != null && curPair.getKey().compareTo(firstKey) != 0) {
            if (map[curIndex].getKey().compareTo(key) == 0)
                return map[curIndex].getValue();

            if (curIndex + 1 < map.length) {
                curIndex++;
            } else {
                curIndex = 0;
            }

            curPair = map[curIndex];
        }
    
        return null;
    }

    
    // Function to insert key-value pair 
    public void put(String key, String value) { 
        // TODO(2)      
        // Insert a new key-value pair into the table.
        if(nElems == map.length)  rehash(); // rehash first if the table is full 
        
        Pair newPair = new Pair(key, value);
        int index = hash(key);

        if (map[index] != null) {
            index++;

            while (map[index] != null) {
                if (index + 1 < map.length) {
                    index++;
                } else {
                    index = 0;
                }
            }
            map[index] = newPair;
        } else {
            map[index] = newPair;
        }
        
        nElems++;
    }
    
    
    /// Function to rehash when the table is full
    public void rehash()  
    {   
		// TODO(3)
        // create a new table twice the size of the old one,
        // then insert all elements from the old hash table to new table.
    	// Hint: 1-backup the reference to the old hash map 
    	//      2-create a new map twice the old size
        //      3-hash all elements from the old hash map to new hash map
        Pair[] oldTable = map;
        map = new Pair[map.length*2];

        for (int i = 0; i < oldTable.length; i++) {
            int index = hash(oldTable[i].getKey());

            if (map[index] == null) {
                map[index] = oldTable[i];
            } else {
                while (map[index] != null) {
                    if (index + 1 < map.length) {
                        index++;
                    } else {
                        index = 0;
                    }
                }
                map[index] = oldTable[i];
            }
        }
  
        
    }
     
 
    // Function to print HashTable 
    public void printHashTable()
    {
        System.out.println("\nHash Table: Key, Value ");
        for (int i = 0; i < maxSize; i++)
            if (map[i] != null)
            	System.out.println(map[i].getKey()+", "+map[i].getValue());
        System.out.println();
    }   
}

class Pair{

	private String key;
	private String value;

	public Pair(String key, String value){
		this.key = key;
		this.value = value;
	}

	public String getKey(){
		return key;
	}

	public String getValue(){
		return value;
	}

}


