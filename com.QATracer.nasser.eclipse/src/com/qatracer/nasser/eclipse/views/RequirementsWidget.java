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

public class RequirementsWidget extends Canvas {

	private final Color color;
	Table table = new Table(getParent(), SWT.MULTI | SWT.BORDER
			| SWT.FULL_SELECTION);

	RequirementsWidget(Composite parent, int style, RGB rgb) {
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
				RequirementsWidget.this.paintControl();
			}
		});

		new Thread("TickTock") {
			public void run() {
				while (!RequirementsWidget.this.isDisposed()) {
					RequirementsWidget.this.getDisplay().asyncExec(
							new Runnable() {
								public void run() {
									if (RequirementsWidget.this != null
											&& !RequirementsWidget.this
													.isDisposed())
										RequirementsWidget.this.table
												.removeAll();
									;
									RequirementsWidget.this.paintControl();

								}
							});
					try {
						Thread.sleep(10000);
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
		RequirementsWidget.this.table
		.removeAll();
		int i = 0;
		String csvloc = QATracerView.getCsvloc();

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

			table.setLinesVisible(true);
			table.setHeaderVisible(true);

			for (int i1 = 0; i1 < titles.length; i1++) {
				TableColumn column = new TableColumn(table, SWT.NONE);
				column.setText(titles[i1]);
			}

			for (int i2 = 0; i2 < i; i2++) {
				for (int j = 0; j < titles.length; j++) {
					System.out.print(RequirMatrix[i2][j] + "   ");

				}
				System.out.println();

			}

			for (int i2 = 0; i2 < i; i2++) {
				TableItem item1 = new TableItem(table, SWT.NONE);
				for (int j = 0; j < titles.length; j++) {

					item1.setText(j, RequirMatrix[i2][j] + " ");
				}
			}
			for (int i1 = 0; i1 < titles.length; i1++) {
				table.getColumn(i1).pack();
			}

			br.close();

		} catch (IOException e2) {
			e2.printStackTrace();
		}
		table.setSize(table.computeSize(SWT.DEFAULT, 200));

	}

}
