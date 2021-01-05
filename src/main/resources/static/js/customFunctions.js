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
 jQuery(".editUserInfos").click(function () {

     var fullname = document.getElementById("fullname").textContent;
     var job = document.getElementById("job").textContent;

     var perso = document.getElementById("perso").getElementsByTagName('li');
     var role = perso[0].innerText;
     var email = perso[1].innerText;
     var phone = perso[2].innerText;
     
     var addressInfos = document.getElementById("address").getElementsByTagName('li');
     var company = addressInfos[0].innerText;

     var addressArray = addressInfos[1].innerText.split(",");
     var address = addressArray[0];
     var city = addressArray[1];

     var country = addressInfos[2].innerText;

     var form = document.getElementById("formEditProfile");

     form.elements["fullname"].value = fullname;
     form.elements["job"].value = job;
     form.elements["role"].value = role;
     form.elements["email"].value = email;
     form.elements["phone"].value = phone;
     form.elements["company"].value = company;
     form.elements["address"].value = address;
     form.elements["city"].value = city;
     form.elements["country"].value = "TN";

 });

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

 /* global flowjs */

 window.addEventListener("load", func);

 function func(){
     console.log("LOADED");



     var c = new flowjs.DiGraph();
     c.addPaths([
         ["Build", "Backend Integration Tests"],["Build", "Frontend Integration Tests"],

         ["Backend Integration Tests", "Remote tests"],
         ["Frontend Integration Tests", "Remote tests"],

         ["Remote tests", "Quality check"],
         [ "Quality check" , "Artifact deployement"],
         ["Artifact deployement","Production deployement"]
     ]);






     // Advanced Example with loading animation
     var cf = new flowjs.DiFlowChart("canvas3", c);
     cf.draw();
     simuLoad(cf, c);
 }


 function simuLoad(flowChart, graph){
     var walker = new flowjs.GraphWalker(graph);
     walker.forEach(function(node){
         var start = Math.random() * 1000 * 5;
         var dur = Math.random() * 1000 * 5;
         simulateLoading(node.id, start);
         simulateDoneLoading(node.id, start + dur);
     }, this);


     function simulateLoading(itemId, timeout){
         setTimeout(function(){
             flowChart.updateItem(itemId, function(item){
                 item.flowItem.toggleFlashing();
             });
         }, timeout);

     }

     function simulateDoneLoading(itemId, timeout){
         setTimeout(function(){
             flowChart.updateItem(itemId, function(item){
                 item.flowItem.toggleFlashing();
                 item.flowItem.color = "#00b894";
                 if (item.connectors === undefined){return;}
                 item.connectors.forEach(function(conn){
                     conn.color = "#00b894";
                 });
             });
         }, timeout);
     }
 }
