/**
 * @license Copyright (c) 2003-2022, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */
//https://ckeditor.com/latest/samples/toolbarconfigurator/index.html#basic

CKEDITOR.editorConfig = function(config) {
//	config.height = 100; // 에디터 높이 조절
	
//	config.toolbarCanCollapse = true; //에디터를 접을 수 있는 기능을 활성화

	//한글폰트 추가
//	config.font_names = '굴림;궁서;돋움;맑은 고딕;바탕;' + CKEDITOR.config.font_names;


	
	config.toolbarGroups = [
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		'/',
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];

	config.removeButtons = 'Save,NewPage,ExportPdf,Preview,Print,Templates,Cut,Copy,Paste,PasteText,PasteFromWord,Replace,SelectAll,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,Form,About,PageBreak,Iframe,Unlink,Link,Anchor,Source,Find,Scayt,Undo,Redo,Blockquote,CreateDiv';
	
};
