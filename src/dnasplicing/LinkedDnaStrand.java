package dnasplicing;

public class LinkedDnaStrand implements DnaStrand {

	int nodeCount = 0;
	int appendCount = 0;
	DnaSequenceNode top, tail;

	public LinkedDnaStrand(String dna) {
		if (dna.isEmpty()) {
			return;
		}
		DnaSequenceNode dnaNode = new DnaSequenceNode(dna);
		top = dnaNode;
		tail = dnaNode;
		nodeCount++;
	}

	public String toString() {
		String dnaString = "";
		DnaSequenceNode cur = top;
		while (cur != null) {
			dnaString = dnaString + cur.dnaSequence.toString();
			cur = cur.next;
		}
		return dnaString;
	}

	@Override
	public long getNucleotideCount() {

		return toString().length();
	}

	@Override
	public void append(String dnaSequence) {
		if (dnaSequence.isEmpty()) {
			return;
		}
		if (top == null) {
			DnaSequenceNode dnaNode = new DnaSequenceNode(dnaSequence);
			top = dnaNode;

			tail = dnaNode;
			nodeCount++;
		} else {
			DnaSequenceNode dnaNode = new DnaSequenceNode(dnaSequence);
			tail.next = dnaNode;
			dnaNode.previous = tail;
			tail = dnaNode;
			appendCount++;
			nodeCount++;
		}

	}

	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		// int cutIndex = top.dnaSequence.indexOf(enzyme);
		// DnaStrand newNode = null;
		// while (cutIndex > -1) {
		// String rest = top.dnaSequence.toString().substring(cutIndex +
		// enzyme.length(),
		// top.dnaSequence.toString().length());
		//
		// LinkedDnaStrand newdnaNode = new LinkedDnaStrand(top.dnaSequence.substring(0,
		// cutIndex));
		// newdnaNode.append(splicee);
		// newdnaNode.append(rest);
		// newNode = newdnaNode;
		// cutIndex = newdnaNode.top.dnaSequence.indexOf(enzyme, cutIndex);
		// }

		/* second option */

		// if (cutIndex != -1) {
		// LinkedDnaStrand newdnaNode = new LinkedDnaStrand(top.dnaSequence.substring(0,
		// cutIndex));
		//
		// DnaSequenceNode splicees = new DnaSequenceNode(splicee);
		//
		// newdnaNode.tail.next = splicees;
		// splicees.previous = newdnaNode.tail;
		// newdnaNode.tail = splicees;
		// nodeCount++;
		//
		// DnaSequenceNode rest = new DnaSequenceNode(
		// top.dnaSequence.substring(cutIndex + enzyme.length(),
		// top.dnaSequence.length()));
		// newdnaNode.tail.next = rest;
		// rest.previous = newdnaNode.tail;
		// newdnaNode.tail = rest;
		// nodeCount++;
		// return newdnaNode;
		// }

		String[] slices = top.dnaSequence.toString().split(enzyme, -1);
		// DnaSequenceNode splicees = new DnaSequenceNode(splicee);
		LinkedDnaStrand clonednaNode = new LinkedDnaStrand(slices[0]);

		for (int x = 1; x < slices.length; x++) {
			clonednaNode.append(splicee);
			clonednaNode.append(slices[x]);
		}
		// if (clonednaNode.toString().split(splicee, -1).length < slices.length) {
		// clonednaNode.appendCount -= 2;
		// } else
		// clonednaNode.appendCount -= 1;
		return clonednaNode;

	}

	@Override
	public DnaStrand createReversedDnaStrand() {

		DnaSequenceNode cur = tail;
		LinkedDnaStrand abc = new LinkedDnaStrand("");
		while (cur != null) {
			abc.append((new StringBuilder(cur.dnaSequence.toString()).reverse().toString()));
			cur = cur.previous;
			// tail = cur;
		}

		// DnaSequenceNode nextNode = null;
		// DnaSequenceNode previousNode = null;
		// while (tail != null) {
		// nextNode = tail.next;
		// tail.next = previousNode;
		// previousNode = tail;
		// tail = nextNode;
		// }
		// top = previousNode;
		// LinkedDnaStrand abc = new LinkedDnaStrand(dnaString);

		return abc;
	}

	@Override
	public int getAppendCount() {
		return appendCount;
	}

	@Override
	public DnaSequenceNode getFirstNode() {

		return top;
	}

	@Override
	public int getNodeCount() {

		return nodeCount;
	}

}
