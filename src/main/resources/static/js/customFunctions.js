jQuery(".editProject").click(function () {

    var rowProject = jQuery(this).closest("tr")[0].cells;
    var projectID = rowProject[0].innerHTML;;
    var projectName = rowProject[1].innerHTML;
    var projectDescription = rowProject[2].firstChild.textContent;

    var pipelinePerProjectString = rowProject[3].childNodes[1].innerText;
    var pipelinePerProjectCount =  pipelinePerProjectString.match(/\d+/);

    var pipelines = rowProject[4].innerHTML;

    var form = document.getElementById("formEditProject");
    form.elements["projectID"].value = projectID;
    form.elements["projectName"].value = projectName;
    form.elements["projectDescription"].value = projectDescription;
    form.elements["pipelinePerProjectCount"].value = pipelinePerProjectCount[0];
    form.elements["pipelines"].value = pipelines;


    //$('.edit-project-modal').modal({show:true});
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
jQuery(".editUserSkills").click(function () {
    var skill1 = document.getElementById("skill1").textContent;
    var skill2 = document.getElementById("skill2").textContent;
    var skill3 = document.getElementById("skill3").textContent;
    var skill4 = document.getElementById("skill4").textContent;
    var skillVal1 = document.getElementById("skillVal1").textContent.replace("%","");
    var skillVal2 = document.getElementById("skillVal2").textContent.replace("%","");
    var skillVal3 = document.getElementById("skillVal3").textContent.replace("%","");
    var skillVal4 = document.getElementById("skillVal4").textContent.replace("%","");
    var form = document.getElementById("formEditSkills");
    form.elements["skill1"].value = skill1;
    form.elements["skill2"].value = skill2;
    form.elements["skill3"].value = skill3;
    form.elements["skill4"].value = skill4;
    form.elements["skillVal1"].value = skillVal1;
    form.elements["skillVal2"].value = skillVal2;
    form.elements["skillVal3"].value = skillVal3;
    form.elements["skillVal4"].value = skillVal4;
    //slider1
    var slider1 = document.getElementById("valueSkill1");
    var output1 = document.getElementById("value1");
    output1.innerHTML = slider1.value + "%";
    slider1.oninput = function() {
        output1.innerHTML = this.value + "%";
    }
//slider2
    var slider2 = document.getElementById("valueSkill2");
    var output2 = document.getElementById("value2");
    output2.innerHTML = slider2.value + "%";
    slider2.oninput = function() {
        output2.innerHTML = this.value + "%";
    }
//slider3
    var slider3 = document.getElementById("valueSkill3");
    var output3 = document.getElementById("value3");
    output3.innerHTML = slider3.value + "%";
    slider3.oninput = function() {
        output3.innerHTML = this.value + "%";
    }
//slider4
    var slider4 = document.getElementById("valueSkill4");
    var output4 = document.getElementById("value4");
    output4.innerHTML = slider4.value + "%";
    slider4.oninput = function() {
        output4.innerHTML = this.value + "%";
    }
});
jQuery(".mybuttonoverlap").click(function () {
    var parentDiv =  jQuery(this).closest("div").parent();
    var childs = parentDiv.children();
    var childDiv = childs[1].children;
    var project = childs[0].innerText;
    var client = childDiv[0].innerText.split(":")[1];
    var date = childDiv[2].innerText.split(":")[1];
    var workPercentage = childDiv[4].innerText.split(":")[1];
    var workload = workPercentage.replace("%","");
    var form = document.getElementById("formEditUserProject");
    form.elements["project"].value = project;
    form.elements["client"].value = client;
    form.elements["date"].value = date;
    form.elements["workload"].value = workload;
    // edit workload slider
    var workSlider1 = document.getElementById("workLoadEdit");
    var workOutput1 = document.getElementById("workLoadEditVal");
    workOutput1.innerHTML = workSlider1.value + "%";
    workSlider1.oninput = function() {
        workOutput1.innerHTML = this.value + "%";
    }
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

function checkConnx() {
      var sURLText = $("#repo_url").val();
   // alert(validURL(sURLText));

   $.post("/checkSCMConnection",{'repositoryUrl': sURLText } ,function () {
             }).fail(function () {
                iziToast.show({
                title: 'Fail',
                message: 'This is a private repository! Dofan user need to be invited. Check documentation for more details https://docs.github.com/en/github/setting-up-and-managing-your-github-user-account/inviting-collaborators-to-a-personal-repository',
                position: 'topRight',
                color: 'red'
            });
        });
}; 

$( "#repo_url").on("focusout",checkConnx);
$('.nav-tabs li').on("click",checkConnx);

$('.eBtn').on('click', function (event) {
    event.preventDefault();
    var href = $(this).attr('href');
    var text = $(this).text(); //return New or Edit

   
        $.get(href, function (credential) {
            $('#formEditCredential #credentialId').val(credential.credentialId);
            $('#formEditCredential #provider').val(credential.provider);
            $('#formEditCredential #username').val(credential.username);
            $('#formEditCredential #email').val(credential.email);
            $('#formEditCredential #password').val(credential.password);
           
        });
        $('.edit-credential-modal').modal({show:true});
    
});


$('.delBtn').on('click', function (event) {
    event.preventDefault();
    var href = $(this).attr('href');
    $('#myModal #delRef').attr('href', href);
    $('#myModal').modal();
});

//calcul price of pack and number of pipelines
$(document).ready(function() {
    //BASIC form configuration
    var formBasic = document.getElementById("formBasic");

    function basicPackConf() {
        var pipelines = formBasic.elements["pipelines"];
        var pipelineVal = formBasic.elements["pipelineVal"];

        var price = formBasic.elements["price"];
        var priceVal = formBasic.elements["priceVal"];

        var currency = document.getElementById("currency");
        var monthly = document.getElementById("monthly");
        var smallVal = workSliderSmall.value;
        var mediumVal = workSliderMedium.value;
        var largeVal = workSliderLarge.value;
        var pipelinesValue = parseInt(smallVal)+ parseInt(mediumVal)+ parseInt(largeVal);
        var priceValue = 19 * (parseInt(smallVal) - 1) + 49 * parseInt(mediumVal) + 139 * parseInt(largeVal);
        if(priceValue === 0) {
            currency.style.display= "none";
            monthly.style.display= "none";
            price.value = 0.00;
            priceVal.value = "FREE";

        } else {
            currency.style.display= "initial";
            monthly.style.display= "initial";
            price.value = priceValue;
            priceVal.value = priceValue;
        }

        pipelineVal.value = pipelinesValue;
        pipelines.value = pipelinesValue;

    }
    // edit workloadSmall slider
    var workSliderSmall = document.getElementById("workLoadSmall");
    var workOutputSmall = document.getElementById("workLoadSmallVal");
    workOutputSmall.innerHTML = workSliderSmall.value;

    workSliderSmall.oninput = function () {
        workOutputSmall.innerHTML = this.value;
        basicPackConf();
    }

    // edit workloadMedium slider
    var workSliderMedium = document.getElementById("workLoadMedium");
    var workOutputMedium = document.getElementById("workLoadMediumVal");
    workOutputMedium.innerHTML = workSliderMedium.value;

    workSliderMedium.oninput = function () {
        workOutputMedium.innerHTML = this.value;
        basicPackConf();
    }

    // edit workloadLarge slider
    var workSliderLarge = document.getElementById("workLoadLarge");
    var workOutputLarge = document.getElementById("workLoadLargeVal");
    workOutputLarge.innerHTML = workSliderLarge.value;

    workSliderLarge.oninput = function () {
        workOutputLarge.innerHTML = this.value;
        basicPackConf();
    }

    //PRO form configuration
    var formPro = document.getElementById("formPro");

    function proPackConf() {
        var pipelines = formPro.elements["pipelines"];
        var pipelineVal = formPro.elements["pipelineVal"];

        var price = formPro.elements["price"];
        var priceVal = formPro.elements["priceVal"];

        var currency = document.getElementById("currencyPro");
        var monthly = document.getElementById("monthlyPro");
        var smallVal = workSliderSmallPro.value;
        var mediumVal = workSliderMediumPro.value;
        var largeVal = workSliderLargePro.value;
        var pipelinesValue = parseInt(smallVal)+ parseInt(mediumVal)+ parseInt(largeVal);
        var priceValue = 29 * (parseInt(smallVal)) + 59 * parseInt(mediumVal) + 159 * parseInt(largeVal);
        if(priceValue === 0) {
            currency.style.display= "none";
            monthly.style.display= "none";
            price.value = 0.00;
            priceVal.value = "FREE";

        } else {
            currency.style.display= "initial";
            monthly.style.display= "initial";
            price.value = priceValue;
            priceVal.value = priceValue;
        }

        pipelineVal.value = pipelinesValue;
        pipelines.value = pipelinesValue;

    }
    // edit workloadSmall slider
    var workSliderSmallPro = document.getElementById("workLoadSmallPro");
    var workOutputSmallPro = document.getElementById("workLoadSmallProVal");
    workOutputSmallPro.innerHTML = workSliderSmallPro.value;

    workSliderSmallPro.oninput = function () {
        workOutputSmallPro.innerHTML = this.value;
        proPackConf();
    }

    // edit workloadMedium slider
    var workSliderMediumPro = document.getElementById("workLoadMediumPro");
    var workOutputMediumPro = document.getElementById("workLoadMediumProVal");
    workOutputMediumPro.innerHTML = workSliderMediumPro.value;

    workSliderMediumPro.oninput = function () {
        workOutputMediumPro.innerHTML = this.value;
        proPackConf();
    }

    // edit workloadLarge slider
    var workSliderLargePro = document.getElementById("workLoadLargePro");
    var workOutputLargePro = document.getElementById("workLoadLargeProVal");
    workOutputLargePro.innerHTML = workSliderLargePro.value;

    workSliderLargePro.oninput = function () {
        workOutputLargePro.innerHTML = this.value;
        proPackConf();
    }
});