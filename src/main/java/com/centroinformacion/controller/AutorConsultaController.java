package com.centroinformacion.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
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

import com.centroinformacion.entity.Autor;
import com.centroinformacion.service.AutorService;
import com.centroinformacion.util.AppSettings;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/url/consultaAutor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AutorConsultaController {
	@Autowired
	private AutorService autorService;

	@GetMapping("/consultaAutorPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaAutorPorParametros(
			@RequestParam(name = "nombres", required = true, defaultValue = "") String nombres,
			@RequestParam(name = "apellidos", required = true, defaultValue = "") String apellidos,
			@RequestParam(name = "fechaNacimientoDesde", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimientoDesde,
			@RequestParam(name = "fechaNacimientoHasta", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimientoHasta,
			@RequestParam(name = "telefono", required = true, defaultValue = "") String telefono,
			@RequestParam(name = "celular", required = true, defaultValue = "") String celular,
			@RequestParam(name = "orcid", required = true, defaultValue = "") String orcid,
			@RequestParam(name = "estado", required = true, defaultValue = "") int estado,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "idGrado", required = false, defaultValue = "-1") int idGrado) {
		List<Autor> lstSalida = autorService.listaConsultaCompleja("%" + nombres + "%", "%" + apellidos + "%",
				fechaNacimientoDesde, fechaNacimientoHasta, "%" + telefono + "%", "%" + celular + "%",
				"%" + orcid + "%", estado, idPais, idGrado);
		return ResponseEntity.ok(lstSalida);
	}

	private static String[] HEADERs = { "CÓDIGO", "NOMBRES", "APELLIDOS", "FECHA NACIMIENTO", "TELEFONO", "CELULAR",
			"ORCID", "ESTADO", "PAÍS", "GRADO" };
	private static String SHEET = "Listado de Autor";
	private static String TITLE = "Listado de Autor - Autora:  Astrid Yovera";
	private static int[] HEADER_WITH = { 3000, 6000, 6000, 4000, 3000, 3000, 5000, 3000, 4000, 4000 };

	@PostMapping("/reporteAutorExcel")
	public void reporteExcel(@RequestParam(name = "nombres", required = true, defaultValue = "") String nombres,
			@RequestParam(name = "apellidos", required = true, defaultValue = "") String apellidos,
			@RequestParam(name = "fechaNacimientoDesde", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimientoDesde,
			@RequestParam(name = "fechaNacimientoHasta", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimientoHasta,
			@RequestParam(name = "telefono", required = true, defaultValue = "") String telefono,
			@RequestParam(name = "celular", required = true, defaultValue = "") String celular,
			@RequestParam(name = "orcid", required = true, defaultValue = "") String orcid,
			@RequestParam(name = "estado", required = true, defaultValue = "") int estado,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "idGrado", required = false, defaultValue = "-1") int idGrado,
			HttpServletRequest request, HttpServletResponse response) {
		try (Workbook excel = new XSSFWorkbook()) {
			// Se crear la hoja del Excel
			Sheet hoja = excel.createSheet(SHEET);
			// Agrupar
			hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADER_WITH.length - 1));

			// Se establece el ancho de las columnas
			for (int i = 0; i < HEADER_WITH.length; i++) {
				hoja.setColumnWidth(i, HEADER_WITH[i]);
			}

			// Fuenta
			Font fuente = excel.createFont();
			fuente.setFontHeightInPoints((short) 10);
			fuente.setFontName("Arial");
			fuente.setBold(true);
			fuente.setColor(IndexedColors.WHITE.getIndex());

			// Estilo
			CellStyle estiloCeldaCentrado = excel.createCellStyle();
			estiloCeldaCentrado.setWrapText(true);
			estiloCeldaCentrado.setAlignment(HorizontalAlignment.CENTER);
			estiloCeldaCentrado.setVerticalAlignment(VerticalAlignment.CENTER);
			estiloCeldaCentrado.setFont(fuente);
			estiloCeldaCentrado.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
			estiloCeldaCentrado.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			// Estilo para datos
			CellStyle estiloDatosCentrado = excel.createCellStyle();
			estiloDatosCentrado.setAlignment(HorizontalAlignment.CENTER);
			estiloDatosCentrado.setVerticalAlignment(VerticalAlignment.CENTER);
			estiloDatosCentrado.setBorderBottom(BorderStyle.THIN);
			estiloDatosCentrado.setBorderTop(BorderStyle.THIN);
			estiloDatosCentrado.setBorderLeft(BorderStyle.THIN);
			estiloDatosCentrado.setBorderRight(BorderStyle.THIN);

			CellStyle estiloDatosIzquierdo = excel.createCellStyle();
			estiloDatosIzquierdo.setAlignment(HorizontalAlignment.LEFT);
			estiloDatosIzquierdo.setVerticalAlignment(VerticalAlignment.CENTER);
			estiloDatosIzquierdo.setBorderBottom(BorderStyle.THIN);
			estiloDatosIzquierdo.setBorderTop(BorderStyle.THIN);
			estiloDatosIzquierdo.setBorderLeft(BorderStyle.THIN);
			estiloDatosIzquierdo.setBorderRight(BorderStyle.THIN);

			// Fila 0
			Row fila1 = hoja.createRow(0);
			Cell celAuxs = fila1.createCell(0);
			celAuxs.setCellStyle(estiloCeldaCentrado);
			celAuxs.setCellValue(TITLE);

			// Fila 1
			Row fila2 = hoja.createRow(1);
			Cell celAuxs2 = fila2.createCell(0);
			celAuxs2.setCellValue("");

			// Fila 2
			Row fila3 = hoja.createRow(2);
			for (int i = 0; i < HEADERs.length; i++) {
				Cell celda1 = fila3.createCell(i);
				celda1.setCellStyle(estiloCeldaCentrado);
				celda1.setCellValue(HEADERs[i]);
			}

			// formato para fecha
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			List<Autor> lstSalida = autorService.listaConsultaCompleja("%" + nombres + "%", "%" + apellidos + "%",
					fechaNacimientoDesde, fechaNacimientoHasta, "%" + telefono + "%", "%" + celular + "%",
					"%" + orcid + "%", estado, idPais, idGrado);
			
			// Fila 3....n
			int rowIdx = 3;
			for (Autor obj : lstSalida) {
				Row row = hoja.createRow(rowIdx++);

				Cell cel0 = row.createCell(0);
				cel0.setCellValue(obj.getIdAutor());

				Cell cel1 = row.createCell(1);
				cel1.setCellValue(obj.getNombres());

				Cell cel2 = row.createCell(2);
				cel2.setCellValue(obj.getApellidos());

				Cell cel3 = row.createCell(3);
				cel3.setCellValue(sdf.format(obj.getFechaNacimiento()));

				Cell cel4 = row.createCell(4);
				cel4.setCellValue(obj.getTelefono());

				Cell cel5 = row.createCell(5);
				cel5.setCellValue(obj.getCelular());

				Cell cel6 = row.createCell(6);
				cel6.setCellValue(obj.getOrcid());

				Cell cel7 = row.createCell(7);
				cel7.setCellValue(obj.getEstado() == 1 ? AppSettings.ACTIVO_DES : AppSettings.INACTIVO_DES);

				Cell cel8 = row.createCell(8);
				cel8.setCellValue(obj.getPais().getNombre());

				Cell cel9 = row.createCell(9);
				cel9.setCellValue(obj.getGrado().getDescripcion());
			}

			// Tipo de archivo y nombre de archivo
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", "attachment; filename=ReporteAutor.xlsx");

			OutputStream outStream = response.getOutputStream();
			excel.write(outStream);
			outStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
