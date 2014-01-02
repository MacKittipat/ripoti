$(document).ready(function(){
	
	$('.edit').click(function(){
		$(this).hide();
		$(this).prev().hide();
		$(this).next().show();
		$(this).next().select();
	});
	
	
	$('input[type="text"].editable').blur(function() {  
         if ($.trim(this.value) == ''){  
			 this.value = (this.defaultValue ? this.defaultValue : '');  
		 }
		 else{
			 $(this).prev().prev().html(this.value);
		 }
		 
		 $(this).hide();
		 $(this).prev().show;
		 $(this).prev().css( "display", "" );
		 $(this).prev().prev().show();
     });
	  
	  $('input[type="text"].editable').keypress(function(event) {
		  if (event.keyCode == '13') {
			  if ($.trim(this.value) == ''){  
				 this.value = (this.defaultValue ? this.defaultValue : '');  
			 }
			 else
			 {
				 $(this).prev().prev().html(this.value);
			 }
			 
			 $(this).hide();
             $(this).prev().show;
             $(this).prev().css( "display", "" );
			 $(this).prev().prev().show();
			 
		  }
	  });
	  
	  
		  
});