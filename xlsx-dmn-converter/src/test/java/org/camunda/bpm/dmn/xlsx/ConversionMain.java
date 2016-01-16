package org.camunda.bpm.dmn.xlsx;

import java.io.InputStream;

import org.camunda.bpm.model.dmn.DmnModelInstance;

public class ConversionMain {

	public static void main(String[] args) {
		XlsxConverter converter = new XlsxConverter();
		InputStream inputStream = TestHelper.getClassPathResource("test1.xlsx");
		DmnModelInstance dmnModelInstance = converter.convert(inputStream);
		TestHelper.writeDmnFile(dmnModelInstance, "test1.dmn");
	}
}
