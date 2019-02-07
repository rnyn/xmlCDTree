package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javafx.scene.control.TreeItem;

public class CDTreeCreator {

	public static TreeItem<String> createTree(String documentPath) throws CDTreeCreatorException {
	
		TreeItem<String> catalogItem = new TreeItem<>();
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();

	
			try {
				doc = builder.build(new File(documentPath));
			Element catalog = doc.getRootElement();
			catalogItem.setValue(catalog.getName());
			List<Element> cds = catalog.getChildren();
			for (Element cd : cds) {
				
				TreeItem<String> cdItem = new TreeItem<>(cd.getChild("TITLE").getValue());
				catalogItem.getChildren().add(cdItem);
				List<Element> cdData = cd.getChildren();
				for (Element entry : cdData) {
					TreeItem<String> entryItem = new TreeItem<String>(entry.getName()+": "+entry.getValue());
					cdItem.getChildren().add(entryItem);
				}
			}
			
			} catch (JDOMException | IOException e) {
				throw new CDTreeCreatorException("CDTreeCreator Exception Thrown!");
			}
		
		return catalogItem;
	}

}
