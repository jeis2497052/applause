package org.applause.lang.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.applause.lang.applauseDsl.ApplauseDslPackage;
import org.applause.lang.applauseDsl.Attribute;
import org.applause.lang.applauseDsl.DataSource;
import org.applause.lang.applauseDsl.DataSourceAccessMethod;
import org.applause.lang.applauseDsl.DataSourceBodySpecification;
import org.applause.lang.applauseDsl.DataType;
import org.applause.lang.applauseDsl.Entity;
import org.applause.lang.applauseDsl.Model;
import org.applause.lang.applauseDsl.Parameter;
import org.applause.lang.applauseDsl.Platform;
import org.applause.lang.applauseDsl.RESTSpecification;
import org.applause.lang.applauseDsl.TypeMapping;
import org.applause.lang.services.ApplauseDslGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class ApplauseDslSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private ApplauseDslGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == ApplauseDslPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ApplauseDslPackage.ATTRIBUTE:
				if(context == grammarAccess.getAttributeRule()) {
					sequence_Attribute(context, (Attribute) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.DATA_SOURCE:
				if(context == grammarAccess.getDataSourceRule() ||
				   context == grammarAccess.getNamedElementRule()) {
					sequence_DataSource(context, (DataSource) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.DATA_SOURCE_ACCESS_METHOD:
				if(context == grammarAccess.getDataSourceAccessMethodRule()) {
					sequence_DataSourceAccessMethod(context, (DataSourceAccessMethod) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.DATA_SOURCE_BODY_SPECIFICATION:
				if(context == grammarAccess.getDataSourceBodySpecificationRule()) {
					sequence_DataSourceBodySpecification(context, (DataSourceBodySpecification) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.DATA_TYPE:
				if(context == grammarAccess.getDataTypeRule() ||
				   context == grammarAccess.getNamedElementRule() ||
				   context == grammarAccess.getTypeRule()) {
					sequence_DataType(context, (DataType) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.ENTITY:
				if(context == grammarAccess.getEntityRule() ||
				   context == grammarAccess.getNamedElementRule() ||
				   context == grammarAccess.getTypeRule()) {
					sequence_Entity(context, (Entity) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.PARAMETER:
				if(context == grammarAccess.getParameterRule()) {
					sequence_Parameter(context, (Parameter) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.PLATFORM:
				if(context == grammarAccess.getNamedElementRule() ||
				   context == grammarAccess.getPlatformRule()) {
					sequence_Platform(context, (Platform) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.REST_SPECIFICATION:
				if(context == grammarAccess.getRESTSpecificationRule()) {
					sequence_RESTSpecification(context, (RESTSpecification) semanticObject); 
					return; 
				}
				else break;
			case ApplauseDslPackage.TYPE_MAPPING:
				if(context == grammarAccess.getPlatformMappingRule() ||
				   context == grammarAccess.getTypeMappingRule()) {
					sequence_TypeMapping(context, (TypeMapping) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (type=[Type|ID] many?='[]'? name=ID)
	 */
	protected void sequence_Attribute(EObject context, Attribute semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID (declaredParameters+=Parameter declaredParameters+=Parameter*)? returnsMany?='[]'? restSpecification=RESTSpecification)
	 */
	protected void sequence_DataSourceAccessMethod(EObject context, DataSourceAccessMethod semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     bodycontents=[Parameter|ID]
	 */
	protected void sequence_DataSourceBodySpecification(EObject context, DataSourceBodySpecification semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ApplauseDslPackage.Literals.DATA_SOURCE_BODY_SPECIFICATION__BODYCONTENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ApplauseDslPackage.Literals.DATA_SOURCE_BODY_SPECIFICATION__BODYCONTENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDataSourceBodySpecificationAccess().getBodycontentsParameterIDTerminalRuleCall_0_1(), semanticObject.getBodycontents());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID baseUrl=STRING resourceType=[Entity|ID] methods+=DataSourceAccessMethod*)
	 */
	protected void sequence_DataSource(EObject context, DataSource semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_DataType(EObject context, DataType semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ApplauseDslPackage.Literals.NAMED_ELEMENT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ApplauseDslPackage.Literals.NAMED_ELEMENT__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDataTypeAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (abstract?='abstract'? name=ID superType=[Entity|ID]? attributes+=Attribute*)
	 */
	protected void sequence_Entity(EObject context, Entity semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     elements+=NamedElement*
	 */
	protected void sequence_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (type=[Type|ID] name=ID)
	 */
	protected void sequence_Parameter(EObject context, Parameter semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ApplauseDslPackage.Literals.PARAMETER__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ApplauseDslPackage.Literals.PARAMETER__TYPE));
			if(transientValues.isValueTransient(semanticObject, ApplauseDslPackage.Literals.PARAMETER__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ApplauseDslPackage.Literals.PARAMETER__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParameterAccess().getTypeTypeIDTerminalRuleCall_0_0_1(), semanticObject.getType());
		feeder.accept(grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID mappings+=PlatformMapping*)
	 */
	protected void sequence_Platform(EObject context, Platform semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (verb=RESTVerb path=STRING body=DataSourceBodySpecification?)
	 */
	protected void sequence_RESTSpecification(EObject context, RESTSpecification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (type=[DataType|ID] simpleName=ID)
	 */
	protected void sequence_TypeMapping(EObject context, TypeMapping semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ApplauseDslPackage.Literals.TYPE_MAPPING__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ApplauseDslPackage.Literals.TYPE_MAPPING__TYPE));
			if(transientValues.isValueTransient(semanticObject, ApplauseDslPackage.Literals.TYPE_MAPPING__SIMPLE_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ApplauseDslPackage.Literals.TYPE_MAPPING__SIMPLE_NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTypeMappingAccess().getTypeDataTypeIDTerminalRuleCall_1_0_1(), semanticObject.getType());
		feeder.accept(grammarAccess.getTypeMappingAccess().getSimpleNameIDTerminalRuleCall_3_0(), semanticObject.getSimpleName());
		feeder.finish();
	}
}
