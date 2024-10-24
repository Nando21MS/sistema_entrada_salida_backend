package com.centroinformacion.controller;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.service.AccesosService;
import com.centroinformacion.util.AppSettings;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/url/verConsultaReporte")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ConsultaReporteController {

    @Autowired
    private AccesosService accesosService;

    @GetMapping("/consultaReporteAccesos")
    @ResponseBody
    public ResponseEntity<?> consultaReporteAccesos(
        @RequestParam(name = "codigo", required = true, defaultValue = "") String codigo,
        @RequestParam(name = "fechaDesde", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
        @RequestParam(name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta,
        @RequestParam(name = "idRol", required = false, defaultValue = "-1") int idRol
    ) {
        System.out.println("Código: " + codigo);
        System.out.println("Fecha Desde: " + fechaDesde);
        System.out.println("Fecha Hasta: " + fechaHasta);
        System.out.println("ID Rol: " + idRol);
        
        List<Accesos> lstSalida = accesosService.listaConsultaCompleja(
            "%" + codigo + "%",
            fechaDesde,
            fechaHasta,
            idRol
        );
        
        return ResponseEntity.ok(lstSalida);
    }

    private static String[] HEADERs = {"CÓDIGO", "NOMBRES", "APELLIDOS", "FECHA", "ROL", "ESTADO"};
    private static String SHEET = "Reporte de Accesos";
    private static String TITLE = "Reporte de Accesos - Entrada y Salida";
    private static int[] HEADER_WIDTH = {3000, 9000, 9000, 6000, 6000, 8000};

    @PostMapping("/reporteAccesos")
    public void reporteExcel(
            @RequestParam(name = "codigo", required = true, defaultValue = "") String codigo,
            @RequestParam(name = "fechaDesde", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam(name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta,
            @RequestParam(name = "idRol", required = false, defaultValue = "-1") int idRol,
            HttpServletRequest request, HttpServletResponse response) {
        try (Workbook excel = new XSSFWorkbook()) {
            Sheet hoja = excel.createSheet(SHEET);
            hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADER_WIDTH.length - 1));

            for (int i = 0; i < HEADER_WIDTH.length; i++) {
                hoja.setColumnWidth(i, HEADER_WIDTH[i]);
            }

            // Estilo de cabecera
            org.apache.poi.ss.usermodel.Font fuente = excel.createFont();
            fuente.setFontHeightInPoints((short) 10);
            fuente.setFontName("Arial");
            fuente.setBold(true);
            fuente.setColor(IndexedColors.WHITE.getIndex());

            CellStyle estiloCeldaCentrado = excel.createCellStyle();
            estiloCeldaCentrado.setWrapText(true);
            estiloCeldaCentrado.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
            estiloCeldaCentrado.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
            estiloCeldaCentrado.setFont(fuente);
            estiloCeldaCentrado.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            estiloCeldaCentrado.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Estilo de datos
            CellStyle estiloDatosCentrado = excel.createCellStyle();
            estiloDatosCentrado.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
            estiloDatosCentrado.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
            estiloDatosCentrado.setBorderBottom(BorderStyle.THIN);
            estiloDatosCentrado.setBorderTop(BorderStyle.THIN);
            estiloDatosCentrado.setBorderLeft(BorderStyle.THIN);
            estiloDatosCentrado.setBorderRight(BorderStyle.THIN);

            // Fila 0
            org.apache.poi.ss.usermodel.Row fila1 = hoja.createRow(0);
            org.apache.poi.ss.usermodel.Cell celAuxs = fila1.createCell(0);
            celAuxs.setCellStyle(estiloCeldaCentrado);
            celAuxs.setCellValue(TITLE);

            // Fila 2 para cabecera
            org.apache.poi.ss.usermodel.Row fila3 = hoja.createRow(2);
            for (int i = 0; i < HEADERs.length; i++) {
                org.apache.poi.ss.usermodel.Cell celda1 = fila3.createCell(i);
                celda1.setCellStyle(estiloCeldaCentrado);
                celda1.setCellValue(HEADERs[i]);
            }

            // Obtener datos
            List<Accesos> lstSalida = accesosService.listaConsultaCompleja(
                "%" + codigo + "%", 
                fechaDesde, 
                fechaHasta, 
                idRol
            );

            int rowIdx = 3;
            for (Accesos obj : lstSalida) {
                org.apache.poi.ss.usermodel.Row row = hoja.createRow(rowIdx++);

                row.createCell(0).setCellValue(obj.getCodigo());
                row.getCell(0).setCellStyle(estiloDatosCentrado);

                row.createCell(1).setCellValue(obj.getNombres());
                row.getCell(1).setCellStyle(estiloDatosCentrado);

                row.createCell(2).setCellValue(obj.getApellidos());
                row.getCell(2).setCellStyle(estiloDatosCentrado);

                row.createCell(3).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(obj.getFecha()));
                row.getCell(3).setCellStyle(estiloDatosCentrado);

                row.createCell(4).setCellValue(obj.getRol());
                row.getCell(4).setCellStyle(estiloDatosCentrado);

                String estadoTexto = obj.getEstado() == 1 ? "Ingresó" : "No Ingresó";
                row.createCell(5).setCellValue(estadoTexto);
                row.getCell(5).setCellStyle(estiloDatosCentrado);
            }

            // Configurar respuesta
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-disposition", "attachment; filename=ReporteAccesos.xlsx");
            
            OutputStream outStream = response.getOutputStream();
            excel.write(outStream);
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}