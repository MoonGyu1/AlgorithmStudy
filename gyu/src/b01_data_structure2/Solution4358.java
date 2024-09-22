// 생태학 (실버2)

package src.b01_data_structure2;

import java.io.*;
import java.util.HashMap;
import java.util.TreeSet;

// 시간복잡도: O(n)
public class Solution4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Integer> hm = new HashMap<>();
        TreeSet<String> ts = new TreeSet<>();
        int total = 0;

        String name = "";
        while((name = br.readLine()) != null && !name.isEmpty()) {
            hm.put(name, hm.getOrDefault(name, 0) + 1);
            ts.add(name);
            total++;
        }

        for(String s: ts){
            double rate = (double) hm.get(s) / total * 100;
            bw.write(String.format("%s %.4f\n", s, rate));
        }

        bw.flush();
        bw.close();
    }
}
