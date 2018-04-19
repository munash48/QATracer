package com.qatracer.nasser.eclipse.views;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.*;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class QATracerView extends ViewPart {
	
	static String csvloc = "";
	static String xmlloc = "";
	String xmltemp = "";
	String Chosenfile = "";

	

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.QATracer.nasser.eclipse.views.qatracer";

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	/**
	 * The constructor.
	 */
	public QATracerView() {

		if (csvloc.equals("")) {
			JFileChooser chooser = new JFileChooser();
			int answer = chooser.showOpenDialog(chooser);
			if (answer == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				Chosenfile = file.getAbsolutePath();
				if ((new File(Chosenfile).getName()).contains(".csv")) {
					csvloc = Chosenfile;
					xmlloc = csvloc.replace(".csv", ".xml");
				} else if ((new File(Chosenfile).getName()).contains(".xml")) {
					xmlloc = Chosenfile;
					csvloc = xmlloc.replace(".xml", ".csv");
				} else {
					String string = "Working file empty or Type not Supported";

					MessageDialog.openInformation(null, "QATracer", string);
				}

			}

		}
	}
	
	public static String getCsvloc(){
		return csvloc;
		
	}
	public static String getXmlloc(){
		return xmlloc;
		
	}
	

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {

		GridLayout grid2layout = new GridLayout();

		grid2layout.numColumns = 4;
		grid2layout.makeColumnsEqualWidth = true;
		grid2layout.verticalSpacing = 10;
		GridData grid2layoutdata = new GridData();
		grid2layoutdata.widthHint = 300;

		final TabFolder tabFolder = new TabFolder(parent, SWT.BORDER_DOT
				| SWT.V_SCROLL | SWT.H_SCROLL);
		Group group2 = new Group(tabFolder, SWT.NONE);
		TabItem item2 = new TabItem(tabFolder, SWT.NONE);

		item2.setText("Set Working Files | Upload Requirements");
		group2.setLayout(grid2layout);

		grid2layoutdata.horizontalAlignment = GridData.FILL;

		Label space1tab2 = new Label(group2, SWT.NATIVE);
		space1tab2.setText(" Working Requirements and Components Files ");
		space1tab2.setLayoutData(grid2layoutdata);

		Label space01tab2 = new Label(group2, SWT.NONE);
		space01tab2.setText("                                              |");
		space01tab2.setLayoutData(grid2layoutdata);
		Label space033tab2 = new Label(group2, SWT.NONE);
		space033tab2.setText("            Add Batch Requirements");
		space033tab2.setLayoutData(grid2layoutdata);

		Label space5tab2 = new Label(group2, SWT.NONE);
		space5tab2.setText("                                              ");
		space5tab2.setLayoutData(grid2layoutdata);

		Label space2tab2 = new Label(group2, SWT.BOLD);
		space2tab2.setLayoutData(grid2layoutdata);
		space2tab2.setText("        Requirements :  "
				+ (new File(csvloc).getName()));

		Label space011tab2 = new Label(group2, SWT.NONE);
		space011tab2.setText("                                              |");
		space011tab2.setLayoutData(grid2layoutdata);

		Label space41tab2 = new Label(group2, SWT.NONE);
		space41tab2
				.setText("                                                  ");
		space41tab2.setLayoutData(grid2layoutdata);

		Label space51tab2 = new Label(group2, SWT.NONE);
		space51tab2.setText("                                              ");
		space51tab2.setLayoutData(grid2layoutdata);

		Label space3tab2 = new Label(group2, SWT.NONE);
		space3tab2.setLayoutData(grid2layoutdata);
		space3tab2.setText("        Components : "
				+ (new File(xmlloc).getName()));

		Label space012tab2 = new Label(group2, SWT.NONE);
		space012tab2.setText("                                              |");
		space012tab2.setLayoutData(grid2layoutdata);

		Text Text2Tab2 = new Text(group2, SWT.NONE | SWT.BORDER);
		Text2Tab2.setSize(400, 64);
		Text2Tab2.setLayoutData(grid2layoutdata);

		Label space52tab2 = new Label(group2, SWT.NONE);
		space52tab2.setText("                                              ");
		space52tab2.setLayoutData(grid2layoutdata);

		Label space7tab2 = new Label(group2, SWT.COLOR_BLUE);
		space7tab2.setText(" Change Working files ");
		space7tab2.setLayoutData(grid2layoutdata);

		Label space0tab2 = new Label(group2, SWT.NONE);
		space0tab2.setText("                                              |");
		space0tab2.setLayoutData(grid2layoutdata);

		Button button2tab2 = new Button(group2, SWT.PUSH);
		button2tab2.setText("                  Browse......              ");
		button2tab2.setLayoutData(grid2layoutdata);
		button2tab2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				int answer = chooser.showOpenDialog(chooser);
				if (answer == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					Text2Tab2.setText(file.getAbsolutePath());
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		Label space8tab2 = new Label(group2, SWT.NONE);
		space8tab2.setText("                                              ");
		space8tab2.setLayoutData(grid2layoutdata);

		Text Text1Tab2 = new Text(group2, SWT.NONE | SWT.BORDER);
		Text1Tab2.setSize(400, 64);
		Text1Tab2.setLayoutData(grid2layoutdata);
		Text1Tab2.setText(csvloc);

		Label space03tab2 = new Label(group2, SWT.NONE);
		space03tab2.setText("                                              |");
		space03tab2.setLayoutData(grid2layoutdata);

		Label space63tab2 = new Label(group2, SWT.NONE);
		space63tab2.setText("                                              ");
		space63tab2.setLayoutData(grid2layoutdata);

		Label space83tab2 = new Label(group2, SWT.NONE);
		space83tab2.setText("                                              ");
		space83tab2.setLayoutData(grid2layoutdata);

		Button button1tab2 = new Button(group2, SWT.PUSH);

		button1tab2.setText("                  Browse......              ");
		button1tab2.setLayoutData(grid2layoutdata);

		button1tab2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				int answer = chooser.showOpenDialog(chooser);
				if (answer == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					Text1Tab2.setText(file.getAbsolutePath());
					Chosenfile = file.getAbsolutePath();
					if ((new File(Chosenfile).getName()).contains(".csv")) {
						csvloc = Chosenfile;
						xmlloc = csvloc.replace(".csv", ".xml");
					} else if ((new File(Chosenfile).getName())
							.contains(".xml")) {
						xmlloc = Chosenfile;
						csvloc = xmlloc.replace(".xml", ".csv");
					} else {
						String string = "Working file empty or Type not Supported";

						MessageDialog.openInformation(null, "QATracer",
								string);
					}
					space2tab2.setText("        Requirements :  "
							+ (new File(csvloc).getName()));
					space3tab2.setText("        Components : "
							+ (new File(xmlloc).getName()));

				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		Label space10tab2 = new Label(group2, SWT.NONE);
		space10tab2.setText("                                              |");
		space10tab2.setLayoutData(grid2layoutdata);

		Button button3tab2 = new Button(group2, SWT.PUSH);
		button3tab2.setText("                  Upload ....              ");
		button3tab2.setLayoutData(grid2layoutdata);
		button3tab2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				// TODO Auto-generated method stub

				int rowCount = 0;
				int rowCount2 = 0;

				String csvFile2 = Text2Tab2.getText();
				String line = "";
				String cvsSplitBy = ",";
				String string = "Please  select a file to upload from";
				if (csvFile2.length() < 2)
					MessageDialog
							.openInformation(item2.getControl().getShell(),
									"QATracer", string);
				else {

					try (BufferedReader br1 = new BufferedReader(
							new FileReader(csvFile2))) {

						while ((line = br1.readLine()) != null) {

							// use comma as separator
							String[] requirement = line.split(cvsSplitBy);
							rowCount++;

							System.out.println(requirement[0] + "   "
									+ requirement[1] + "   " + requirement[2]
									+ " " + requirement[3] + " " + rowCount);

						}

						try (BufferedReader br = new BufferedReader(
								new FileReader(csvFile2))) {

							while ((line = br.readLine()) != null) {

								// use comma as separator
								rowCount2++;

								if (rowCount2 != 0 & rowCount2 <= rowCount) {
									String ReqLine = line + "\n";
									System.out
											.println("The current line is ...... "
													+ ReqLine);
								

									FileWriter pw2 = new FileWriter(csvloc,
											true);

									pw2.append(ReqLine);

									pw2.flush();
									pw2.close();

								}

								String[] requirement = line.split(cvsSplitBy);

								System.out.println(requirement[0] + "   "
										+ requirement[1] + "   "
										+ requirement[2] + "   "
										+ requirement[3] + " " + rowCount2);

							}

						} catch (IOException e3) {
							e3.printStackTrace();
						}

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Text2Tab2.setText("");
					;
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		Label space02tab2 = new Label(group2, SWT.NONE);
		space02tab2.setText("                                              ");
		space02tab2.setLayoutData(grid2layoutdata);

		Button button22tab2 = new Button(group2, SWT.Deactivate);

		button22tab2.setLayoutData(grid2layoutdata);


		item2.setControl(group2);

		/*
		 * End of tab one
		 * 
		 * 
		 * Start of tab two
		 */

		Rectangle clientArea = parent.getClientArea();

		tabFolder.setLocation(clientArea.x, clientArea.y);

		GridLayout gridlayout1 = new GridLayout();
		gridlayout1.numColumns = 2;
		Group group3 = new Group(tabFolder, SWT.NONE);
		TabItem item3 = new TabItem(tabFolder, SWT.NONE);
		item3.setText("Quality Requirements View ");

		group3.setLayout(gridlayout1);

		// TODO Auto-generated method stub

		final RequirementsWidget RQtable = new RequirementsWidget(group3, SWT.NONE, new RGB(0,
				0, 255));
		RQtable.setLayout(gridlayout1);

		item3.setControl(group3);

		/*
		 * 
		 * 
		 * End of tab two
		 * 
		 * 
		 * Start of tab three
		 */

		Group group4 = new Group(tabFolder, SWT.NONE);
		TabItem item4 = new TabItem(tabFolder, SWT.NONE);
		item4.setText("Componets View");
		group4.setLayout(gridlayout1);

		

		final ComponetsWidget Comptable = new ComponetsWidget(group4, SWT.NONE, new RGB(0,
				0, 255));
		Comptable.setLayout(gridlayout1);
		
		

		

		item4.setControl(group4);
		
		Group group5 = new Group(tabFolder, SWT.NONE);
		TabItem item5 = new TabItem(tabFolder, SWT.NONE);
		item5.setText("Reuirement Matrix View");
		group5.setLayout(gridlayout1);
		
		Label space63tab4 = new Label(group5, SWT.NONE);
		space63tab4.setText("                                              ");
		
		Label title1 = new Label(group5, SWT.NONE);
		title1.setText("        COMPONENTS      ");
		
		
		Label title2 = new Label(group5, SWT.NONE);
		title2.setText("REQUIREMENTS");
		
		
		
		
		Label space83tab4 = new Label(group5, SWT.NONE);
		space83tab4.setText("                                              ");

		
		final MatrixWidget Matrixtable = new MatrixWidget(group5, SWT.NONE, new RGB(0,
				0, 255));
		Matrixtable.setLayout(gridlayout1);

		Matrixtable.table.addListener(SWT.MouseDown, new Listener(){

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				String string = "";

				TableItem[] selection = Matrixtable.table.getSelection();
				
				System.out.println("selection ############# "+selection);
				for (int i = 0; i < selection.length; i++)
					string += selection[i] + ",";
				string = string.replace('{', ',');
				string = string.replaceAll(" ", ",Tulumbe");
				string = string.replace('}', ' ');
				String[] splitstring = string.split(Matrixtable.cvsSplitBy);
				for (int i = 0; i < splitstring.length; i++)
					if (splitstring[i].charAt(0) == 'U') {
						string = splitstring[i];
					}
				String UID = string;
				System.out.println("UID ############# "+UID);
				String userr = "";
				String ftr = "";
				String desc = "";
				String comps = "";

				System.out.println("RQrowCount3......   " + Matrixtable.rowCount4 + "Matrixtable.maxcomp " +Matrixtable.maxcomp);

				for (int i2 = 0; i2 < Matrixtable.rowCount4; i2++) {
					if (Matrixtable.RequirMatrix[i2][0].equals(UID)) {
						userr = Matrixtable.RequirMatrix[i2][1];
						ftr = Matrixtable.RequirMatrix[i2][2];
						desc = Matrixtable.RequirMatrix[i2][3];

					}
					for (int j = 0; j < 4; j++) {

						System.out.print(Matrixtable.RequirMatrix[i2][j] + " >>>>>>>>>>>>>>>>  ");

					}
					System.out.println();

				}
				
				System.out.println("Matrixtable.i ............................  " + Matrixtable.i);
			

				for (int i2 = 0; i2 < Matrixtable.i; i2++) {
					for (int j = 0; j < Matrixtable.maxcomp; j++) {
						System.out.println("Matrixtable.ComponentMatrix[i2][j]>>>>>>>>>>>>>>>\n                                ................... " +Matrixtable.ComponentMatrix[i2][j] + " [i2] is "+ i2 + "[j] is + "+j);

						if ((UID.trim().toString())
								.equalsIgnoreCase(Matrixtable.ComponentMatrix[i2][j]
										.trim().toString())) {
							comps += "> " + Matrixtable.ComponentMatrix[i2][0] + "\n";
							System.out
									.println("comps contains     >>>>" + comps);
						}

						

					}
					System.out.println();

				}
			
				

				string = "REQUIREMENT DETAILS \n\n"
						+ "URID                 : " + UID
						+ "\nUSER                : " + userr
						+ "\nFeature            : " + ftr
						+ "\nDescription     : " + desc
						+ "\n\nCOMPONENTS WHICH FULL FILL IT \n\n" + comps;

				MessageDialog.openInformation(item5.getControl()
						.getShell(), "QATracer", string);
			}
			
		});
		
		

		
		
		

		

		item5.setControl(group5);

	}

	public void setFocus() {

	}

}