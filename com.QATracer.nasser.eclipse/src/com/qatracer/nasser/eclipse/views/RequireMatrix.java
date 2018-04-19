package com.qatracer.nasser.eclipse.views;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

public class RequireMatrix  {


	

	
		

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

	// button22tab22.setLayoutData(grid2layoutdata);

	public String [][] creatReqMatrix() {

		int i = 0;
		String csvloc ="C:/Users/KYAMBOGO/runtime-EclipseApplication/sample/scholarship.csv";

		String[][] RequirMatrix;

		String line = "";
		String cvsSplitBy = ",";

		String[] titles = { "   UR_ID   ", " USER ", "  Feature  ",
				"    Description   " };

		int RQrowCount3 = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(csvloc))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] requirement = line.split(cvsSplitBy);
				RQrowCount3++;

				System.out.println(requirement[0] + "   " + requirement[1]
						+ "   " + requirement[2] + "  " + requirement[3]
						+ RQrowCount3);

			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		RequirMatrix = new String[RQrowCount3][4];
		try (BufferedReader br = new BufferedReader(new FileReader(csvloc))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] requirement = line.split(cvsSplitBy);

				for (int j = 0; j < 4; j++) {
					RequirMatrix[i][j] = requirement[j];

				}

				i++;

			}

	

	

			for (int i2 = 0; i2 < i; i2++) {
				for (int j = 0; j < titles.length; j++) {
					System.out.print(RequirMatrix[i2][j] + "   ");

				}
				System.out.println();

			}

		
		
			br.close();

		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return RequirMatrix;


	}

}
