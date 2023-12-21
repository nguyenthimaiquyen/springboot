$(document).ready(function () {

    $('#user-page-size').change(function (event) {
        const pageSize = event.target.value;
        window.location.href = ('/users?pageSize=' + pageSize + '&currentPage=0');
    });

});