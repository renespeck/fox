package org.aksw.fox.tools.ner.en;

import java.io.IOException;
import java.util.Properties;

import org.aksw.fox.data.EntityClassMap;
import org.aksw.fox.nerlearner.reader.TrainingInputReader;
import org.aksw.fox.tools.ner.common.StanfordCommon;

/**
 *
 * @author rspeck
 *
 */
public class StanfordEN extends StanfordCommon {

  private static Properties props = new Properties();
  static {
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
    props.setProperty("tokenize.language", "en");
    props.setProperty("ner.applyNumericClassifiers", "false");
    props.setProperty("ner.useSUTime", "false");
    props.setProperty("ner.model",
        "edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz");
  }

  public StanfordEN() {
    super(props);

    entityClasses.put("ORGANIZATION", EntityClassMap.O);
    entityClasses.put("LOCATION", EntityClassMap.L);
    entityClasses.put("PERSON", EntityClassMap.P);
    entityClasses.put("PEOPLE", EntityClassMap.P);
    entityClasses.put("O", EntityClassMap.N);
  }

  public static void main(final String[] a) {
    final TrainingInputReader tr = new TrainingInputReader();
    try {
      tr.initFiles("input/4");
    } catch (final IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    LOG.info(new StanfordEN().retrieve(tr.getInput()));

    // LOG.info(new StanfordEN().retrieve(FoxConst.NER_EN_EXAMPLE_1));
  }
}
