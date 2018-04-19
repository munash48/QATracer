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

public class ComponetsWidget extends Canvas {
	private final Color color;
	Table table = new Table(getParent(), SWT.MULTI | SWT.BORDER
			| SWT.FULL_SELECTION);

	ComponetsWidget(Composite parent, int style, RGB rgb) {
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
				ComponetsWidget.this.paintControl();
			}
		});

		new Thread("TickTock") {
			public void run() {
				while (!ComponetsWidget.this.isDisposed()) {
					ComponetsWidget.this.getDisplay().asyncExec(new Runnable() {
						public void run() {
							if (ComponetsWidget.this != null
									&& !ComponetsWidget.this.isDisposed())
								ComponetsWidget.this.table.removeAll();
							ComponetsWidget.this.paintControl();

						}
					});
					try {
						Thread.sleep(11000);
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
		ComponetsWidget.this.table.removeAll();
		int i = 0;
	
		String xmlloc = QATracerView.getXmlloc();

		String[][] ComponentMatrix;
	
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

			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			String[] titles = new String[maxcomp];
			titles[0] = "  COMPONENT NAME";
			titles[1] = "  REQUIMENT 1 ";
			if (maxcomp > 1)
				for (int t = 2; t < maxcomp; t++)
					titles[t] = "REQUIREMENT " + t;

			for (int i1 = 0; i1 < titles.length; i1++) {
				TableColumn column = new TableColumn(table, SWT.NONE);
				column.setText(titles[i1]);
			}

			for (int i2 = 0; i2 < i; i2++) {
				TableItem item1 = new TableItem(table, SWT.NONE);
				for (int j = 0; j < maxcomp; j++) {

					item1.setText(j, ComponentMatrix[i2][j] + " ");
				}
			}
			for (int i1 = 0; i1 < titles.length; i1++) {
				table.getColumn(i1).pack();
			}
			table.setSize(table.computeSize(SWT.DEFAULT, 400));

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
