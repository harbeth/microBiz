package com.microBiz.controller;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.controller.Navigation;
import org.slim3.util.ResponseLocator;

import com.pdfjet.*;

public class PdfTestController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        PDF pdf = new PDF(
            new BufferedOutputStream(
                ResponseLocator.get().getOutputStream()));

        Font fs = new Font(pdf, CoreFont.HELVETICA);
        Font fbig = new Font(pdf, CoreFont.HELVETICA_BOLD);
        Font flg = new Font(pdf, CoreFont.HELVETICA_BOLD);
        Font f4 = new Font(pdf, CoreFont.HELVETICA_OBLIQUE);
        
        fs.setSize(8);
        fbig.setSize(14);
        flg.setSize(20);
        f4.setSize(10);

    Page page = new Page(pdf, Letter.PORTRAIT);
    
    Image image1 = new Image(
        pdf,
        getClass().getResourceAsStream("/images/logoW.png"),
        ImageType.PNG);
    image1.setLocation(50f, 50f);
    image1.drawOn(page);
  
    TextLine text0 = new TextLine(fbig,
            "Foam Expert");
    text0.setLocation(150f, 70f);
    text0.drawOn(page);
    
    TextLine text1 = new TextLine(fs,
            "570 Alden Road, Unit 3, Markham, ON L3R 8N5");
    text1.setLocation(140f, 90f);
    text1.drawOn(page);
    
    TextLine text2 = new TextLine(fs,
            "Phone: 905 604 4566");
    text2.setLocation(150f, 100f);
    text2.drawOn(page);
    
    TextLine title = new TextLine(flg,
            "INVOICE");
    title.setLocation(410f, 70f);
    title.drawOn(page);
    
    List<List<Cell>> tableData = new ArrayList<List<Cell>>();
    List<Cell> row1 = new ArrayList<Cell>();
    List<Cell> row2 = new ArrayList<Cell>();
    Cell cell1 = new Cell(f4, "Date");
    Cell cell2 = new Cell(f4, "Invoice #");
    Cell cell3 = new Cell(f4, "2014/12/12");
    Cell cell4 = new Cell(f4, "456987");
    row1.add(cell1);
    row1.add(cell2);
    row2.add(cell3);
    row2.add(cell4);
    tableData.add(row1);
    tableData.add(row2);
    Table table = new Table();
    table.setData(tableData, Table.DATA_HAS_2_HEADER_ROWS);
    table.setLocation(410f, 90f);
    table.wrapAroundCellText();
    table.drawOn(page);
    // REPLACED:
    // table.setCellMargin(2f);

    TextLine billTo = new TextLine(fbig,
            "Bill To:");
    billTo.setLocation(50f, 150f);
    billTo.drawOn(page);
   
    StringBuilder buf = new StringBuilder();
    buf.append("Dominion Drywall company \n");
    buf.append("address here \n");
    buf.append("phone number here \n");

    // TextBox textBox = new TextBox(f1, buf.toString(), 400f, 363f);
    TextBox textBox = new TextBox(f4, buf.toString(), 300f, 50f);
    textBox.setLocation(50f, 160f);
    textBox.setVerticalAlignment(TextAlign.TOP);
    textBox.setNoBorders();
    // textBox.setVerticalAlignment(TextAlign.BOTTOM);
    // textBox.setVerticalAlignment(TextAlign.CENTER);

    textBox.drawOn(page);
    
    List<List<Cell>> poTableData = new ArrayList<List<Cell>>();
    List<Cell> poR1 = new ArrayList<Cell>();
    List<Cell> poR2 = new ArrayList<Cell>();
    Cell cellpo1 = new Cell(f4, "Customer PO #");
    Cell cellpo2 = new Cell(f4, "Job Address");
    Cell cellpo3 = new Cell(f4, "Completion Date");
    Cell cellpo4 = new Cell(f4, "456987dag");
    Cell cellpo5 = new Cell(f4, "sth stre  toronto northjk ");
    Cell cellpo6 = new Cell(f4, "2015/01/01");
    poR1.add(cellpo1);
    poR1.add(cellpo2);
    poR1.add(cellpo3);
    poR2.add(cellpo4);
    poR2.add(cellpo5);
    poR2.add(cellpo6);


    poTableData.add(poR1);
    poTableData.add(poR2);
    Table poTable = new Table();
    poTable.setData(poTableData, Table.DATA_HAS_2_HEADER_ROWS);
    poTable.setLocation(50f, 230f);
    poTable.setColumnWidth(0, 110f);
    poTable.setColumnWidth(1, 300f);
    poTable.setColumnWidth(2, 100f);
    poTable.wrapAroundCellText();
    poTable.drawOn(page);
    
    
    List<List<Cell>> itemsTableData = new ArrayList<List<Cell>>();
    List<Cell> itemsH = new ArrayList<Cell>();
    List<Cell> items1 = new ArrayList<Cell>();
    List<Cell> items2 = new ArrayList<Cell>();
    Cell cellH1 = new Cell(f4, "Description");
    Cell cellH2 = new Cell(f4, "Qyt");
    Cell cellH3 = new Cell(f4, "Rate");
    Cell cellH4 = new Cell(f4, "Amount");
    cellH1.setBgColor(Color.lightgray);
    cellH2.setBgColor(Color.lightgray);
    cellH3.setBgColor(Color.lightgray);
    cellH4.setBgColor(Color.lightgray);
    cellH1.setNoBorders();
    cellH2.setNoBorders();
    cellH3.setNoBorders();
    cellH4.setNoBorders();
    
    itemsH.add(cellH1);
    itemsH.add(cellH2);
    itemsH.add(cellH3);
    itemsH.add(cellH4);
    
    Cell cellIR1 = new Cell(f4, "skfdskgds");
    cellIR1.setNoBorders();
    Cell cellIR2 = new Cell(f4, "230");
    cellIR2.setNoBorders();
    Cell cellIR3 = new Cell(f4, "5.23");
    cellIR3.setNoBorders();
    Cell cellIR4 = new Cell(f4, "1453");
    cellIR4.setNoBorders();
    items1.add(cellIR1);
    items1.add(cellIR2);
    items1.add(cellIR3);
    items1.add(cellIR4);
    
    Cell cellIR11 = new Cell(f4, "skfdskgds");
    cellIR11.setNoBorders();
    Cell cellIR12 = new Cell(f4, "230");
    cellIR12.setNoBorders();
    Cell cellIR13 = new Cell(f4, "5.23");
    cellIR13.setNoBorders();
    Cell cellIR14 = new Cell(f4, "1453");
    cellIR14.setNoBorders();
    items2.add(cellIR11);
    items2.add(cellIR12);
    items2.add(cellIR13);
    items2.add(cellIR14);


    itemsTableData.add(itemsH);
    itemsTableData.add(items1);
    itemsTableData.add(items2);

    Table itemTable = new Table();
    itemTable.setData(itemsTableData, Table.DATA_HAS_2_HEADER_ROWS);
    itemTable.setLocation(50f, 280f);
    itemTable.setColumnWidth(0, 330f);
    itemTable.setColumnWidth(1, 60f);
    itemTable.setColumnWidth(2, 60f);
    itemTable.setColumnWidth(3, 64f);
    itemTable.wrapAroundCellText();
    Point p = itemTable.drawOn(page);
    
    
    List<List<Cell>> totalTableData = new ArrayList<List<Cell>>();
    List<Cell> totalR1 = new ArrayList<Cell>();
    List<Cell> totalR2 = new ArrayList<Cell>();
    List<Cell> totalR3 = new ArrayList<Cell>();
    List<Cell> totalR4 = new ArrayList<Cell>();
    
    Cell cellT11 = new Cell(f4,"Sub Total:");
    cellT11.setNoBorders();
    Cell cellT12 = new Cell(f4,"12365");
    cellT12.setNoBorders();
    cellT12.setLeftPadding(22);
    totalR1.add(cellT11);
    totalR1.add(cellT12);
    
    Cell cellT21 = new Cell(f4,"Discount:");
    cellT21.setNoBorders();
    Cell cellT22 = new Cell(f4,"236");
    cellT22.setNoBorders();
    cellT22.setLeftPadding(22);
    totalR2.add(cellT21);
    totalR2.add(cellT22);
    
    Cell cellT31 = new Cell(f4,"HST(#45944326678):");
    cellT31.setNoBorders();
    Cell cellT32 = new Cell(f4,"236");
    cellT32.setLeftPadding(22);
    cellT32.setNoBorders();
    totalR3.add(cellT31);
    totalR3.add(cellT32);
    
    Cell cellT41 = new Cell(f4,"Total:");
    cellT41.setNoBorders();
    Cell cellT42 = new Cell(f4,"2360");
    cellT42.setNoBorders();
    cellT42.setLeftPadding(22);
    
    totalR4.add(cellT41);
    totalR4.add(cellT42);
    totalTableData.add(totalR1);
    totalTableData.add(totalR2);
    totalTableData.add(totalR3);
    totalTableData.add(totalR4);
    
    Table totalTable = new Table();
    totalTable.setData(totalTableData, Table.DATA_HAS_2_HEADER_ROWS);
    totalTable.setLocation(p.getX()+100,p.getY()+20);
    totalTable.setColumnWidth(0, 330f);
    totalTable.setColumnWidth(1, 100f);
    totalTable.setTextAlignInColumn(0,  Align.RIGHT);

    totalTable.wrapAroundCellText();
    totalTable.drawOn(page);
    
    pdf.close();
    return null;
    }
}
