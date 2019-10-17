$('.numeric').keypress(function(event) {
   if(event.which < 46 || event.which > 59) {
     event.preventDefault();
   } // prevent if not number/dot

   if(event.which == 46 && $(this).val().indexOf('.') != -1) {
     event.preventDefault();
   } // prevent if already dot
});