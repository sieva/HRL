<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<html>   
    <%@page import="java.io.File"%>
    <%@page import="rw.motardmis.domain.Umusanzu"%> 
    <%@page import="javax.swing.JOptionPane"%>
    <!Doctype.html>
    <!Doctype.html>
    <%@page import="java.sql.PreparedStatement"%>
    <%@page import="java.sql.DriverManager"%>
    <%@page import="java.sql.Connection"%>
    <%@page import="com.itextpdf.text.FontFactory"%>
    <%@page import="com.itextpdf.text.BaseColor"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.util.List"%>
    <%@page import="com.itextpdf.text.Image"%>
    <%@page import="com.itextpdf.text.Chunk"%>
    <%@page import="com.itextpdf.text.Font"%>
    <%@page import="com.itextpdf.text.Rectangle"%>
    <%@page import="com.itextpdf.text.pdf.draw.LineSeparator"%>
    <%@page import="com.itextpdf.text.Element"%>
    <%@page import="com.itextpdf.text.Paragraph"%>
    <%@page import="com.itextpdf.text.Phrase"%>
    <%@page import="com.itextpdf.text.pdf.PdfPCell"%>
    <%@page import="com.itextpdf.text.pdf.PdfWriter"%>
    <%@page import="com.itextpdf.text.pdf.PdfPTable"%>
    <%@page import="com.itextpdf.text.PageSize"%>
    <%@page import="com.itextpdf.text.Document"%>
    <%@page import="java.text.SimpleDateFormat"%>
    <%@page import="java.util.Date"%>

    <body style="background: #fed200">
        <%
            Date date = new Date();
            response.setContentType("application/pdf");
            Document document = new Document(new Rectangle(700, 800));
            

            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph(new Date().toString()));

            String path = "C:\\Users\\Evariste\\Desktop" + "Umusanzu" + ".pdf";
            File f = new File("C:\\Users\\Evariste\\Desktop" + "Umusanzu" + ".pdf");
            if (f.exists()) {
                f.delete();   
            }

            Font font2 = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD | Font.UNDERLINE | Element.ALIGN_MIDDLE, BaseColor.ORANGE);
            Font font1 = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD | Element.ALIGN_TOP | Element.AUTHOR);
            LineSeparator UNDERLINE = new LineSeparator(2, 9, null, Element.ALIGN_CENTER, -1);
            Font font3 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD | Element.ALIGN_CENTER);
            Font font4 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD | Element.ALIGN_CENTER);
            Font font5 = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD | Element.ALIGN_CENTER | Font.UNDEFINED);
            Font font6 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Element.ALIGN_CENTER);
            Paragraph par2 = new Paragraph(new Chunk("", font1));

            Paragraph par = new Paragraph(new Chunk("TODAY'S REPORT OF UMUSORO", font2));

            par.setAlignment(Element.ALIGN_CENTER);
            document.addAuthor("SIBOMANA Evariste");
            document.add(par);

            document.add(new Paragraph("\n"));
            PdfPTable tablee = new PdfPTable(4);
            tablee.setWidthPercentage(110);

            PdfPTable tablee3 = new PdfPTable(1);
            tablee3.addCell(new Phrase(new Chunk("PERMIT NO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD, BaseColor.GREEN))));
            PdfPTable tablee4 = new PdfPTable(1);
            tablee4.addCell(new Phrase(new Chunk("PLATE NO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.BOLD, BaseColor.GREEN))));
            PdfPTable tablee5 = new PdfPTable(1);
            tablee5.addCell(new Phrase(new Chunk("AMOUNT", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.BOLD, BaseColor.GREEN))));
            PdfPTable tablee6 = new PdfPTable(1);
            tablee6.addCell(new Phrase(new Chunk("DEPOSIT DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.BOLD, BaseColor.GREEN))));

            tablee.addCell(tablee3);
            tablee.addCell(tablee4);
            tablee.addCell(tablee5);
            tablee.addCell(tablee6);

            document.add(tablee);

            try {

                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/motardmis_db", "root", "");
                String sql = "select *from Umusanzu where DATE(deposit_Date)=date(NOW()) ";
                /*SELECT * FROM myTable WHERE DATE(myDate) = DATE(NOW())*/
                PreparedStatement pst = c.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    PdfPTable table = new PdfPTable(4);
                    table.setWidthPercentage(110);

                    PdfPTable table3 = new PdfPTable(1);
                    table3.addCell(new Phrase(new Chunk(rs.getString(6) + "", font5)));

                    PdfPTable table4 = new PdfPTable(1);
                    table4.addCell(new Phrase(new Chunk(rs.getString(7) + "", font5)));
                    PdfPTable table5 = new PdfPTable(1);
                    table5.addCell(new Phrase(new Chunk(rs.getDouble(2) + "", font5)));
                    PdfPTable table6 = new PdfPTable(1);
                    table6.addCell(new Phrase(new Chunk(rs.getDate(5) + "", font5)));

                    table.addCell(table3);
                    table.addCell(table4);
                    table.addCell(table5);
                    table.addCell(table6);

                    document.add(table);
                }
                String sql1 = "select sum(Amount)as Total from Umusanzu";
                PreparedStatement ps = c.prepareStatement(sql1);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    /// Paragraph par9 = new Paragraph(new Chunk("Umu", font2));
                    // document.add(par9);  
                }

                document.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            //=====================================================

        %>


    </body>
</html>