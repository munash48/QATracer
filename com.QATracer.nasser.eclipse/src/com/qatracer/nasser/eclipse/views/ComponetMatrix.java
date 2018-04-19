package com.qatracer.nasser.eclipse.views;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

public class ComponetMatrix {
	

	public Point computeSize(int w, int h, boolean changed) {
		int size;
		if (w == SWT.DEFAULT) {
			size = h;

		} else if (h == SWT.DEFAULT) {
			size = w;
		} else {
			size = Math.min(w, h);
		}
		if (size == SWT.DEFAULT)
			size = 50;
		return new Point(size, size);
	}

	private int offset;

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}
	String xmlloc = "C:\\Users\\KYAMBOGO\\runtime-EclipseApplication\\sample\\scholarship.xml";
	// button22tab22.setLayoutData(grid2layoutdata);
	String[][] ComponentMatrix;

	public String [][] createMatrix() throws FileNotFoundException, IOException {
		
		

		
	
		int maxcomp = 0;
		int rowCount3 = 0;

		String line = "";
		String cvsSplitBy = ",";

		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader(xmlloc))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator

				rowCount3++;

				System.out.println(line.trim());

			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] selection1 = new String[rowCount3];

		try (BufferedReader br = new BufferedReader(new FileReader(xmlloc))) {

			int i = 0;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String tline = line.trim();

				if (tline.charAt(0) == 's' & tline.charAt(14) == 'n') {
					for (int j = 0; j < tline.length(); j++)
						if (tline.charAt(j) == ','
								& j == tline.lastIndexOf(',')) {
							selection1[i] = tline;
							i++;
						}
					System.out.println(tline);

				}

			}
			System.out
					.println(" \n\nSELECTED LINES ARE\n .................................... ");

			String[] csvline = new String[i];

			for (int j = 0; j < i; j++) {
				System.out.println(selection1[j]);

				int x1 = selection1[j].indexOf('"');
				int x2 = selection1[j].lastIndexOf('"');
				System.out.println(x1 + "  " + x2);
				csvline[j] = selection1[j].substring(x1 + 1, x2);
				System.out.println(csvline[j]);

			}
			for (int j = 0; j < i; j++) {
				String[] Component1 = csvline[j].split(cvsSplitBy);
				if (maxcomp < Component1.length)
					maxcomp = Component1.length;
			}
			ComponentMatrix = new String[i][maxcomp];

			for (int j = 0; j < i; j++) {
				String[] Component = csvline[j].split(cvsSplitBy);

				for (int k = 0; k < maxcomp; k++) {

					if (k >= Component.length)

						ComponentMatrix[j][k] = "     ";
					else
						ComponentMatrix[j][k] = Component[k];

				}
			}
			System.out
					.println(" \n\n component matrix formart\n.................................... ");
			for (int i2 = 0; i2 < i; i2++) {
				for (int j = 0; j < maxcomp; j++) {
					System.out.print(ComponentMatrix[i2][j] + "   ");

				}
				System.out.println();

			}

		
			String[] titles = new String[maxcomp];
			titles[0] = "  COMPONENT NAME";
			titles[1] = "  REQUIMENT 1 ";
			if (maxcomp > 1)
				for (int t = 2; t < maxcomp; t++)
					titles[t] = "REQUIREMENT " + t;

		
			
		

	}
		return ComponentMatrix;
	}
}

