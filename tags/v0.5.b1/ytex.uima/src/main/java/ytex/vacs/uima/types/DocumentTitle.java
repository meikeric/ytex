

/* First created by JCasGen Wed Apr 28 21:48:01 EDT 2010 */
package ytex.vacs.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** covered text is the title
 * Updated by JCasGen Wed Apr 28 21:48:01 EDT 2010
 * XML source: E:/projects/VA/ytex/src/ytex/vacs/uima/types/DocumentInfoTypeSystem.xml
 * @generated */
public class DocumentTitle extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DocumentTitle.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DocumentTitle() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DocumentTitle(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DocumentTitle(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DocumentTitle(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
}

    