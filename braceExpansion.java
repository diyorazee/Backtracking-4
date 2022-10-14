// Time Complexity : O((n/k)^k)
// Space Complexity : O(n)

public class braceExpansion {
    public static void main(String[] args) {
        String s = "{a,b}cd{z,x,y}";
        String[] arr = expand(s);
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    static List<String> result;
    public static String[] expand(String s){
        result = new ArrayList<>();
        int n = s.length();
        List<List<Character>> blocks = new ArrayList<>();
        int i=0;
        while(i<n){
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i)!=','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else{
                block.add(c);
            }
            Collections.sort(block);
            blocks.add(block);
            i++;
        }
        backtrack(blocks, 0, new StringBuilder());
        String[] array = new String[result.size()];
        for(int k = 0; k<result.size(); k++){
            array[k] = result.get(k);
        }
        return array;
    }

    private static void backtrack(List<List<Character>> blocks, int idx, StringBuilder str){
        //base
        if(idx == blocks.size()){
            result.add(str.toString());
            return;
        }

        //logic
        List<Character> block = blocks.get(idx);
        for(int i=0; i<block.size(); i++){
            char c = block.get(i);
            //action
            str.append(c);
            //recurse
            backtrack(blocks, idx+1, str);
            //backtarck
            str.setLength(str.length()-1);
        }
    }
}