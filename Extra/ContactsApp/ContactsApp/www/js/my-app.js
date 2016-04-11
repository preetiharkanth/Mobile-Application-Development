// Initialize your app
var myApp = new Framework7();

// Export selectors engine
var $$ = Dom7;

// Add view
var mainView = myApp.addView('.view-main', {
    // Because we use fixed-through navbar we can enable dynamic navbar
    dynamicNavbar: true
});

console.log('Iam here');

// Callbacks to run specific code for specific pages, for example for About page:
myApp.onPageInit('about', function (page) {
    // run createContentPage func after link was clicked
    $$('.create-page').on('click', function () {
        createContentPage();
    });
});

function submit(){
    var name=document.getElementsByTagName("input")[0].value;
    var mobile=document.getElementsByTagName("input")[1].value;
    var work=document.getElementsByTagName("input")[2].value;
    var home=document.getElementsByTagName("input")[3].value;
    var email=document.getElementsByTagName("input")[4].value;
    var url=document.getElementsByTagName("input")[5].value;
    var bdate=document.getElementsByTagName("input")[6].value;
    var photo=document.getElementsByTagName("input")[7].value;
    var contact=navigator.contacts.create({"displayName": name});
    var phoneNumbers = [];
    phoneNumbers[0] = new ContactField('work', work, false);
    phoneNumbers[1] = new ContactField('mobile', mobile, true); // preferred number
    phoneNumbers[2] = new ContactField('home', home, false);
    contact.phoneNumbers = phoneNumbers;
    console.log('Iam here in submit');
    var emails=[];
    emails[0]=new ContactField('email',email,true);
    contact.emails=emails;
    var urls=[];
    urls[0]=new ContactField('url',url,true);
    contact.urls=urls;
    contact.birthday=bdate;
    var photos=[];
    photos[0]=new ContactField('photo',photo,true);
    contact.photos=photos;
    contact.save();
    alert('Save Success');
    
}

function getpicture(){
    navigator.camera.getPicture(onSuccess, onFail, { quality: 50, sourceType:Camera.PictureSourceType.SAVEDPHOTOALBUM,
    destinationType: Camera.DestinationType.FILE_URI });
    
    function onSuccess(imageData){
        var photo=document.getElementsByTagName("input")[7];
        photo.value=imageData;
    }
    function onFail(message){
    condole.log(message);
    }
    
}

function cancel(){
    window.location.href="index.html";
}

// Generate dynamic page
var dynamicPageIndex = 0;
function createContentPage() {
	mainView.router.loadContent(
        '<!-- Top Navbar-->' +
        '<div class="navbar">' +
        '  <div class="navbar-inner">' +
        '    <div class="left"><a href="#" class="back link"><i class="icon icon-back"></i><span>Back</span></a></div>' +
        '    <div class="center sliding">Dynamic Page ' + (++dynamicPageIndex) + '</div>' +
        '  </div>' +
        '</div>' +
        '<div class="pages">' +
        '  <!-- Page, data-page contains page name-->' +
        '  <div data-page="dynamic-pages" class="page">' +
        '    <!-- Scrollable page content-->' +
        '    <div class="page-content">' +
        '      <div class="content-block">' +
        '        <div class="content-block-inner">' +
        '          <p>Here is a dynamic page created on ' + new Date() + ' !</p>' +
        '          <p>Go <a href="#" class="back">back</a> or go to <a href="services.html">Services</a>.</p>' +
        '        </div>' +
        '      </div>' +
        '    </div>' +
        '  </div>' +
        '</div>'
    );
	return;
}