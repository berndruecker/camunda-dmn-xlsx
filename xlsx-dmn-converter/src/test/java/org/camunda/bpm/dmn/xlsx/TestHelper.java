/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.dmn.xlsx;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;

import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.dmn.instance.Decision;
import org.camunda.bpm.model.dmn.instance.DecisionTable;
import org.camunda.bpm.model.xml.impl.util.IoUtil;
import org.junit.Assert;

/**
 * @author Thorben Lindhauer
 *
 */
public class TestHelper {

  public static InputStream getClassPathResource(String path) {
    return TestHelper.class.getClassLoader().getResourceAsStream(path);
  }

  public static DecisionTable assertAndGetSingleDecisionTable(DmnModelInstance dmnModel) {
    Assert.assertNotNull(dmnModel.getDefinitions());
    Collection<Decision> decisions = dmnModel.getDefinitions().getChildElementsByType(Decision.class);
    Assert.assertEquals(1, decisions.size());

    Decision decision = decisions.iterator().next();
    Assert.assertNotNull(decision);

    Collection<DecisionTable> decisionTables = decision.getChildElementsByType(DecisionTable.class);
    Assert.assertEquals(1, decisionTables.size());

    return decisionTables.iterator().next();
  }
  
  public static void writeDmnFile(DmnModelInstance modelInstance, String name) {
	  try {
		  FileOutputStream os = new FileOutputStream(name);
		  IoUtil.writeDocumentToOutputStream(modelInstance.getDocument(), os);
		  os.close();
	  } catch (Exception ex) {
		  throw new RuntimeException("Could not write DMN to file '" + name + "'", ex);
	  }
  }

}
