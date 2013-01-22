
Ext.Loader.setPath({
	"Spark.content":mainWeb+'/app/cms/admin/content',
	"Spark.desktop":mainWeb+'/app/cms/admin/desktop'
})
Ext.application({
	controllers:['Spark.desktop.controller.Desktop']
});