/**
 * Created by bhargavsarvepalli on 28/10/15.
 */
function toggleInputMailList() {
    ml = document.getElementById("input_mail_list");
    if (ml.style.display == 'none') {
        ml.style.display = 'block'
    } else {
        ml.style.display = 'none';
    }
}

function uploadTemplate() {
    document.getElementById("template_file").click();
}

function uploadList() {
    document.getElementById("list_file").click();
}

function uploadOnChange(evnt) {
    var filename = evnt.target.value;
    var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);
    }
    if (filename != '') {
        hideRequired(evnt.target);
    }
    document.getElementById(evnt.target.id+'_name').innerHTML = filename;
    if (evnt.target.id == 'template_file') {
        handleFileSelect(evnt);
    } else if(evnt.target.id == 'list_file') {
        document.getElementById("list_name").value = filename;
    }
}

var templateString;

function handleFileSelect(event) {
    var input = event.target; // FileList object

    var reader = new FileReader();
    reader.onload = function () {
        var text = reader.result;
        var node = document.getElementById('mail_render_view');
        node.contentDocument.write(text);
        node.style.display = 'block';
        templateString = text;
    };
    reader.readAsText(input.files[0]);
}

function validate() {
    if (document.getElementById("campaign_name").value == '' || document.getElementById("campaign_name").value == null) {
        findAncestor(document.getElementById("campaign_name"), 'mdl-grid').getElementsByClassName("required")[0].style.display = 'block';
        document.getElementById("campaign_name").focus();
        return false;
    } else if (document.getElementById("creative_name").value == '' || document.getElementById("creative_name").value == null) {
        findAncestor(document.getElementById("creative_name"), 'mdl-grid').getElementsByClassName("required")[0].style.display = 'block';
        document.getElementById("creative_name").focus();
        return false;
    } else if (document.getElementById("subject").value == '' || document.getElementById("subject").value == null) {
        findAncestor(document.getElementById("subject"), 'mdl-grid').getElementsByClassName("required")[0].style.display = 'block';
        document.getElementById("subject").focus();
        return false;
    } else if (document.getElementById("sender").value == '' || document.getElementById("sender").value == null) {
        findAncestor(document.getElementById("sender"), 'mdl-grid').getElementsByClassName("required")[0].style.display = 'block';
        document.getElementById("sender").focus();
        return false;
    } else if (document.getElementById("template_file").value == '' || document.getElementById("template_file").value == null) {
        findAncestor(document.getElementById("template-name"), 'mdl-grid').getElementsByClassName("required")[0].style.display = 'block';
        return false;
    } else if (document.getElementById("existing_list").checked == false &&
        (document.getElementById("list_file").value == '' || document.getElementById("list_file").value == null)) {
        findAncestor(document.getElementById("list-name"), 'mdl-grid').getElementsByClassName("required")[0].style.display = 'block';
        return false;
    }
    return true;
}

function testMail() {
    if (validate()) {
        displayOffList = document.getElementsByClassName("form-active-display");
        for (i = 0; i < displayOffList.length; i++) {
            displayOffList[i].style.display = 'none';
        }
        displayOnList = document.getElementsByClassName("form-active-no-display");
        for (i = 0; i < displayOnList.length; i++) {
            displayOnList[i].style.display = 'block';
        }

        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                alert(xmlHttp.responseText);
            }
        }
        xmlHttp.open("POST", "/mail", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.setRequestHeader("Content-Encoding", "gzip");
        xmlHttp.send("campaign_name=" + document.getElementById("campaign_name").value + "&creative_name=" + document.getElementById("creative_name").value +
        "&sender=" + document.getElementById("sender").value + "&subject=" + document.getElementById("subject").value + "&template=" + templateString);
    }
}

function findAncestor(el, cls) {
    while ((el = el.parentElement) && !el.classList.contains(cls));
    return el;
}

function hideRequired(elem) {
    findAncestor(elem, 'mdl-grid').getElementsByClassName("required")[0].style.display = 'none';
}



