package com.qatracer.nasser.eclipse.views;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MatrixWidget extends Canvas {

	private final Color color;
	Table table = new Table(getParent(), SWT.MULTI | SWT.BORDER
			| SWT.FULL_SELECTION);
	int rowCount4 = 0;

	String[][] ComponentMatrix ;

	String[][] RequirMatrix;
	int i = 0;

	String xmlloc = QATracerView.getXmlloc();

	int maxcomp = 0;
	int rowCount3 = 0;

	String line = "";
	String cvsSplitBy = ",";

	MatrixWidget(Composite parent, int style, RGB rgb) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		this.color = new Color(parent.getDisplay(), rgb);
		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				// TODO Auto-generated method stub
				if (color != null && !color.isDisposed())
					color.dispose();

			}

		});
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				MatrixWidget.this.paintControl();
			}
		});
		
		MatrixWidget.this.table.removeAll();
		
		
		    

		new Thread("TickTock") {
			public void run() {
				while (!MatrixWidget.this.isDisposed()) {
					MatrixWidget.this.getDisplay().asyncExec(new Runnable() {
						public void run() {
							if (MatrixWidget.this != null
									&& !MatrixWidget.this.isDisposed())
								MatrixWidget.this.table.removeAll();
							
							MatrixWidget.this.paintControl();

						}
					});
					try {
						Thread.sleep(12000);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		}.start();

	}

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
	
	public void paintControl() {

		MatrixWidget.this.table.removeAll();
		rowCount3=0;
		i=0;

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
		
		System.out.println("ROW COUNT THREE CONTAINS:----------------- "+ rowCount3);
		String[] selection1 = new String[rowCount3];

		try (BufferedReader br = new BufferedReader(new FileReader(xmlloc))) {

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
					System.out.println(tline    + "Line number :::::: "+i);

				}

			}
			System.out
					.println(" \n\nSELECTED LINES ARE\n .................................... \n ####### MATRIX WIDGET ###### ");
			System.out.println("i here contains ....>" + i);

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
					.println(" \n\n component matrix formart\n  ######################## \n IN MAtrix Widget ");
			for (int i2 = 0; i2 < i; i2++) {
				for (int j = 0; j < maxcomp; j++) {
					System.out.print(ComponentMatrix[i2][j] + "   ");

				}
				System.out.println();

			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String csvloc = QATracerView.getCsvloc();
		rowCount4=0;

		try (BufferedReader br = new BufferedReader(new FileReader(csvloc))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				@SuppressWarnings("unused")
				String[] requirement = line.split(cvsSplitBy);
				rowCount4++;

				

			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		RequirMatrix = new String[rowCount4][4];

		try (BufferedReader br = new BufferedReader(new FileReader(csvloc))) {

			rowCount4 = 0;

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] requirement = line.split(cvsSplitBy);

				for (int j = 0; j < requirement.length; j++) {
					RequirMatrix[rowCount4][j] = requirement[j];

				}

				rowCount4++;

			}

			System.out
					.println(" \n\n Requirements matrix formart\n  ######################## \n IN MAtrix Widget : i is " +i );
			for (int i2 = 0; i2 < rowCount4; i2++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(RequirMatrix[i2][j] + "   ");

				}
				System.out.println();

			}
			

			

			table.setLinesVisible(true);
			table.setHeaderVisible(true);

			String[] titles = new String[i + 1];
			System.out.println(i);
			System.out.println(rowCount4);
			titles[0] = "   ";
			for (int i3 = 1; i3 < i + 1; i3++){
				titles[i3] = ComponentMatrix[i3 - 1][0];
				System.out.println(" Componets titles are : "+titles[i3]+ " ");
				
			}

			for (int i1 = 0; i1 < titles.length; i1++) {
				TableColumn column = new TableColumn(table, SWT.NONE);
				column.setText(titles[i1]);
			}

			String marcher = "||||||||||||||";
			String holder = "         ";

			for (int i2 = 0; i2 < rowCount4; i2++) {
				TableItem item1 = new TableItem(table, SWT.NONE);
				int k = 0;

				int j = 0;
				if (j == 0) {
					holder = RequirMatrix[i2][j];
					item1.setText(k, holder + " ");
					System.out.println(item1 + " inside if(j=0) of i2 & j "
							+ i2 + " & " + j);
					k++;

				}
				System.out.println("i contains in TableItems : " +i);
				System.out.println("maxcomp contains in TableItems : " +maxcomp);

				for (int y = 0; y < i; y++) {
					for (int x = 1; x < maxcomp; x++) {
						try{
						if (((ComponentMatrix[y][x]).trim())
								.equalsIgnoreCase((RequirMatrix[i2][0]).trim())) {
							holder = marcher;
							System.out.println("ComponentMatrix[y][x] contains in TableItems : " +ComponentMatrix[y][x]);
							System.out.println("RequirMatrix[i2][0] contains in TableItems : " +RequirMatrix[i2][0]);
						}

					} catch (Exception e){
						System.out.println(e.toString());
					}
					} 
					if (holder.equals(marcher)) {
						item1.setText(k, holder);
						holder = "        ";
					}

					else {
						holder = "      ";
						item1.setText(k, holder);
					}
					k++;
				}

			}

			for (int i1 = 0; i1 < titles.length; i1++) {
				table.getColumn(i1).pack();
			}
			table.setSize(table.computeSize(SWT.DEFAULT, 200));
			
			br.close();

		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// table.setSize(table.computeSize(SWT.DEFAULT, 200));

	}

}
