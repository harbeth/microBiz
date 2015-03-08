package com.microBiz.controller.pub;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.ResponseLocator;

import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.OrderService;
import com.microBiz.service.QuoteService;
import com.pdfjet.Align;
import com.pdfjet.Cell;
import com.pdfjet.Color;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.Image;
import com.pdfjet.ImageType;
import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Point;
import com.pdfjet.Table;
import com.pdfjet.TextAlign;
import com.pdfjet.TextBox;
import com.pdfjet.TextLine;

public class QuoteToPdfController extends BaseController {
    private QuoteService quoteService;
    private OrderService orderService;
    
    public QuoteToPdfController(){
        super();
        quoteService = new QuoteService();
        orderService = new OrderService();
    }
    @Override
    public Navigation run() throws Exception {
        PDF pdf = new PDF(
            new BufferedOutputStream(
                ResponseLocator.get().getOutputStream()));
        
        Quote quote = quoteService.get(Datastore.stringToKey(asString("quoteKey"))); 
        QuoteOrder quoteOrder = quote.getQuoteOrderRef().getModelList().get(0);
        Order order = quoteOrder.getOrderRef().getModel();
        
        Font fs = new Font(pdf, CoreFont.HELVETICA);
        Font fbig = new Font(pdf, CoreFont.HELVETICA_BOLD);
        Font flg = new Font(pdf, CoreFont.HELVETICA_BOLD);
        Font f4 = new Font(pdf, CoreFont.HELVETICA_OBLIQUE);
        
        fs.setSize(10);
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
            "570 Alden Road, Unit 3,");
    text1.setLocation(140f, 90f);
    text1.drawOn(page);
    
    TextLine text3 = new TextLine(fs,
            "Markham, ON L3R 8N5");
    text3.setLocation(140f, 100f);
    text3.drawOn(page);
    
    TextLine text2 = new TextLine(fs,
            "Phone: 905 604 4566");
    text2.setLocation(140f, 110f);
    text2.drawOn(page);
    
    TextLine title = new TextLine(flg,
            "Quotation");
    title.setLocation(410f, 70f);
    title.drawOn(page);
    
    List<List<Cell>> tableData = new ArrayList<List<Cell>>();
    List<Cell> row1 = new ArrayList<Cell>();
    List<Cell> row2 = new ArrayList<Cell>();
    Cell cell1 = new Cell(f4, "Date");
    Cell cell2 = new Cell(f4, "Version");
    Cell cell3 = new Cell(f4, quote.getCreatedAtStr());
    Cell cell4 = new Cell(f4, quoteOrder.getName());
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
            "For:");
    billTo.setLocation(50f, 150f);
    billTo.drawOn(page);
   
    StringBuilder buf = new StringBuilder();
    buf.append(quote.getCustomerRef().getModel().getName() +"\n");
    buf.append("Address: \n");
    buf.append(quote.getAddress() +"\n");
   

    // TextBox textBox = new TextBox(f1, buf.toString(), 400f, 363f);
    TextBox textBox = new TextBox(f4, buf.toString(), 300f, 50f);
    textBox.setLocation(50f, 160f);
    textBox.setVerticalAlignment(TextAlign.TOP);
    textBox.setNoBorders();
    // textBox.setVerticalAlignment(TextAlign.BOTTOM);
    // textBox.setVerticalAlignment(TextAlign.CENTER);

    textBox.drawOn(page);
 
    
    List<List<Cell>> itemsTableData = new ArrayList<List<Cell>>();
    List<Cell> itemsH = new ArrayList<Cell>();

    Cell cellH1 = new Cell(f4, "Description");
    Cell cellH2 = new Cell(f4, "Rate");
    Cell cellH3 = new Cell(f4, "Qty");
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
    itemsTableData.add(itemsH);
    
    List<OrderItem> items = orderService.gerOrderItems(order.getKey());
    Iterator<OrderItem> i = items.iterator();
    Double subTotal = new Double(0);
    while (i.hasNext()){
        List<Cell> items1 = new ArrayList<Cell>();
        OrderItem oi = i.next();
        Cell cellIR1 = new Cell(f4, oi.getProductRef().getModel().getModel() + " :  "+ oi.getDesc());
        cellIR1.setNoBorders();
        Cell cellIR2 = new Cell(f4, oi.getRate().toString());
        cellIR2.setNoBorders();
        Cell cellIR3 = new Cell(f4, oi.getQty().toString());
        cellIR3.setNoBorders();
        Double total = oi.getQty()*oi.getRate();
        subTotal = subTotal+total;
        Cell cellIR4 = new Cell(f4, total.toString());
        cellIR4.setNoBorders();
        items1.add(cellIR1);
        items1.add(cellIR2);
        items1.add(cellIR3);
        items1.add(cellIR4);
        itemsTableData.add(items1);
    }


    Table itemTable = new Table();
    itemTable.setData(itemsTableData, Table.DATA_HAS_2_HEADER_ROWS);
    itemTable.setLocation(50f, 230f);
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
    
    Cell cellT11 = new Cell(f4,"Sub Total: ");
    cellT11.setNoBorders();
    Cell cellT12 = new Cell(f4,subTotal.toString());
    cellT12.setNoBorders();
    cellT12.setLeftPadding(22);
    totalR1.add(cellT11);
    totalR1.add(cellT12);
    
    Cell cellT21 = new Cell(f4,"Discount:");
    cellT21.setNoBorders();
    Cell cellT22 = new Cell(f4,order.getDiscount().toString());
    cellT22.setNoBorders();
    cellT22.setLeftPadding(22);
    totalR2.add(cellT21);
    totalR2.add(cellT22);
    
    Cell cellT31 = new Cell(f4,"HST(#45944326678):");
    cellT31.setNoBorders();
    double taxAmt = MicroBizUtil.roundTo2Demcial(order.getTaxRate()*subTotal);
    Cell cellT32 = new Cell(f4,Double.toString(taxAmt));
    cellT32.setLeftPadding(22);
    cellT32.setNoBorders();
    totalR3.add(cellT31);
    totalR3.add(cellT32);
    
    Cell cellT41 = new Cell(f4,"Total:");
    cellT41.setNoBorders();
    Cell cellT42 = new Cell(f4,order.getTotal().toString());
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
