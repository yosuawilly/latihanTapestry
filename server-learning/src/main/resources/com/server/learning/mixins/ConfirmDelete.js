var ConfirmDelete = Class.create( {
	initialize : function(clientId, message) {
		Event.observe($(clientId), 'click', this.doConfirmDelete.bindAsEventListener(this, clientId, message));
		//$('#'+clientId).click(this.doConfirmDelete(this, clientId, message));
	},
	
	doConfirmDelete : function(e, clientId, message) {
		e.stop();
		jQuery("#konfirmasi").dialog({
            title: "Konfirmasi",
            resizable: false,
            position: 'center',
            modal: true,
            width: 360,
            height: 140,
//            hide: 'fold',
            hide: 'explode',
            show: 'clip',
            buttons:[
                {
                    text:"Ok",
                    click:function(){
                        window.location.href = jQuery("#"+clientId).attr('href');
                    }
                },
                {
                    text:"Batal",
                    click:function(){
                    	jQuery(this).dialog("close");
                    }
                }
            ]
        });

//		if(!confirm(message)) {
//			e.stop();
//		}
		
		return false;
	}
} );

Tapestry.Initializer.confirm = function(spec) {
    new ConfirmDelete(spec.elementId, spec.message);
};

//jQuery(document).ready(function(){
//new ConfirmDelete('eventlink','Coba');
//});

//(function ($){
//    T5.extendInitializers(function(){
//    	alert("test");
//          function confirmation(spec){
//                $("#"+spec.id).bind("click", function(e){                      
//                      if(!confirm(spec.message))
//                           e.preventDefault();
//                });
//          }
//          return { confirmation : confirmation};
//    });
//}) (jQuery);