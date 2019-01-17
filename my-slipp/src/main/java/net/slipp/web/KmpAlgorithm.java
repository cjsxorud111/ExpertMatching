package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

public class KmpAlgorithm {

	Integer[] naiveSearch(char[] pattern, char[] text) {
		List<Integer> res = new ArrayList<>();
		NAIVE_SEARCH_USING_JUMP: for (int i = 0; i <= text.length - pattern.length; i++) {
			for (int j = 0; j < pattern.length; j++) {
				if (pattern[j] != text[i + j])
					continue NAIVE_SEARCH_USING_JUMP;
			}
			res.add(i);
		}
		return res.toArray(new Integer[0]);
	}

	public Integer[] kmpSearch(char[] ptrn, char[] txt) {
		if (ptrn.length >= txt.length)
			return new Integer[0];

		/*** [START] calculate pmt (partial match table) ***/
		int[] pmt = new int[ptrn.length + 1];
		pmt[0] = -1;
		for (int i = 1; i < ptrn.length + 1; i++) { // i is MISMATCH position
			PMT_LOOP: for (int j = 0; j < i; j++) { // j is length
				for (int k = 0; k < j; k++) { // k for indexing ptrn
					if (ptrn[k] != ptrn[i - j + k]) // if not with this j
						continue PMT_LOOP; // go to next j
				}
				pmt[i] = j; // definitely MAX value because j is increasing
			}
		}
		/*** [ END ] calculate pmt (partial match table) ***/

		/*** [START] search pattern in text using pmt ***/
		List<Integer> res = new ArrayList<>();

		int i = 0, j = 0;
		KMP_LOOP: while (i <= (txt.length - ptrn.length)) { // i is pointer for txt
			while (j < ptrn.length) { // j is pointer for ptrn
				if (ptrn[j] != txt[i + j]) { // if MISMATCHED
					i += (j - pmt[j]); // JUMP !!!!!!!!!
					j = (j == 0) ? 0 : pmt[j]; // SKIP !!!!!!!!!
					continue KMP_LOOP; // continue with new i, j
				} else { // if MATCHED
					j++; // just j++
				}
			}
			res.add(i); // AT HERE, FULLY MATCHED
			i += (ptrn.length - pmt[ptrn.length]); // to next i
			j = pmt[ptrn.length]; // to next j
		}
		/*** [ END ] search pattern in text using pmt ***/

		return res.toArray(new Integer[0]);
	}
}