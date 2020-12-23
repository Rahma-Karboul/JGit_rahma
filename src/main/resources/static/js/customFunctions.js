 jQuery(".editUser").click(function () {
     jQuery("#checkEdit div label input").each(function () {
           var checkVal = this.parentElement.classList.contains("checked");
           if(checkVal === true){
                this.parentElement.parentElement.click();
           }
      });
     var rowValue = jQuery(this).closest("tr")[0].cells;
     var FirstName = rowValue[0].innerHTML;
     var LastName = rowValue[1].innerHTML;
     var Email = rowValue[2].innerHTML;
     var status = rowValue[3].firstChild.title;
     var admin = rowValue[4].innerHTML;
     var date = rowValue[5].innerHTML;
     var projects = rowValue[6].getElementsByTagName("li");

     var form = document.getElementById("formEdit");

     form.elements["First Name"].value = FirstName;
     form.elements["Last Name"].value = LastName;
     form.elements["email"].value = Email;
      form.elements["status"].value = status;
      var checkValue = document.getElementById("checkbox").checked;
     if(admin === "Admin") {
     if(checkValue ===  false) {
          ///// valide: $("#checkbox").parent()[0].click();
          form.role.parentElement.click();

     }
     } else if (admin === "User") {
          if(checkValue ===  true) {
               form.role.parentElement.click();
          }
     }
     form.elements["Start Date"].value = date;
for(var i in projects){
     //valide: form.project1.parentElement
     var projectVal = projects[i].innerHTML;
     var t = "#" + projectVal;
    jQuery(t).parent()[0].click();
}
 });
 jQuery(".editProject").click(function () {

      var rowProject = jQuery(this).closest("tr")[0].cells;
      var projectName = rowProject[0].firstChild.textContent;
      var description = rowProject[1].innerHTML;

      var form = document.getElementById("formEditProject");

      form.elements["projectName"].value = projectName;
      form.elements["description"].value = description;
 });
/* jQuery("button:cancel").click(function (){
      jQuery('#checkEdit div label input').each(function () {
           var checkVal = this.parentElement.classList.contains('checked');
           if(checkVal === true){
           this.parentElement.parentElement.click();
           }
      });
 });*/

 $(document).on("click","#emoji-picker",function(e){
     e.stopPropagation();
     $('.intercom-composer-emoji-popover').toggleClass("active");
 });

 $(document).click(function (e) {
     if ($(e.target).attr('class') != '.intercom-composer-emoji-popover' && $(e.target).parents(".intercom-composer-emoji-popover").length == 0) {
         $(".intercom-composer-emoji-popover").removeClass("active");
     }
 });

 $(document).on("click",".intercom-emoji-picker-emoji",function(e){
     //$(".test-emoji").append($(this).html());
     $("#test-emoji").append('<span class="image">'+$(this).html()+'</span>');
 });

 $('.intercom-composer-popover-input').on('input', function() {
     var query = this.value;
     if(query != ""){
         $(".intercom-emoji-picker-emoji:not([title*='"+query+"'])").hide();
     }
     else{
         $(".intercom-emoji-picker-emoji").show();
     }
 });
