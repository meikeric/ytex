

/* First created by JCasGen Thu May 31 11:19:55 EDT 2012 */
package ytex.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Thu May 31 11:19:55 EDT 2012
 * XML source: ../config/desc/ytex/uima/YTEXTypes.xml
 * @generated */
public class KeyValuePair extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(KeyValuePair.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected KeyValuePair() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public KeyValuePair(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public KeyValuePair(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: key

  /** getter for key - gets 
   * @generated */
  public String getKey() {
    if (KeyValuePair_Type.featOkTst && ((KeyValuePair_Type)jcasType).casFeat_key == null)
      jcasType.jcas.throwFeatMissing("key", "ytex.uima.types.KeyValuePair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KeyValuePair_Type)jcasType).casFeatCode_key);}
    
  /** setter for key - sets  
   * @generated */
  public void setKey(String v) {
    if (KeyValuePair_Type.featOkTst && ((KeyValuePair_Type)jcasType).casFeat_key == null)
      jcasType.jcas.throwFeatMissing("key", "ytex.uima.types.KeyValuePair");
    jcasType.ll_cas.ll_setStringValue(addr, ((KeyValuePair_Type)jcasType).casFeatCode_key, v);}    
   
    
  //*--------------*
  //* Feature: valueLong

  /** getter for valueLong - gets 
   * @generated */
  public long getValueLong() {
    if (KeyValuePair_Type.featOkTst && ((KeyValuePair_Type)jcasType).casFeat_valueLong == null)
      jcasType.jcas.throwFeatMissing("valueLong", "ytex.uima.types.KeyValuePair");
    return jcasType.ll_cas.ll_getLongValue(addr, ((KeyValuePair_Type)jcasType).casFeatCode_valueLong);}
    
  /** setter for valueLong - sets  
   * @generated */
  public void setValueLong(long v) {
    if (KeyValuePair_Type.featOkTst && ((KeyValuePair_Type)jcasType).casFeat_valueLong == null)
      jcasType.jcas.throwFeatMissing("valueLong", "ytex.uima.types.KeyValuePair");
    jcasType.ll_cas.ll_setLongValue(addr, ((KeyValuePair_Type)jcasType).casFeatCode_valueLong, v);}    
   
    
  //*--------------*
  //* Feature: valueString

  /** getter for valueString - gets 
   * @generated */
  public String getValueString() {
    if (KeyValuePair_Type.featOkTst && ((KeyValuePair_Type)jcasType).casFeat_valueString == null)
      jcasType.jcas.throwFeatMissing("valueString", "ytex.uima.types.KeyValuePair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KeyValuePair_Type)jcasType).casFeatCode_valueString);}
    
  /** setter for valueString - sets  
   * @generated */
  public void setValueString(String v) {
    if (KeyValuePair_Type.featOkTst && ((KeyValuePair_Type)jcasType).casFeat_valueString == null)
      jcasType.jcas.throwFeatMissing("valueString", "ytex.uima.types.KeyValuePair");
    jcasType.ll_cas.ll_setStringValue(addr, ((KeyValuePair_Type)jcasType).casFeatCode_valueString, v);}    
  }

    