$(document).ready(function () {

    toastr.options.timeOut = 2500; // 2.5s

    let deleteSubjectId = -1;
    let updateSubjectId = -1;

    // get subject types
    $.ajax({
        url: "/subjects/type",
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            const subjectTypeSelection = $('#subject-creation-modal #subjectType, #subject-update-modal #subjectType');
            if (subjectTypeSelection.children().length === 0) {
                if (!data || data.length === 0) {
                    return;
                }
                let subjectTypeOptions = "";
                for (let i = 0; i < data.length; i++) {
                    subjectTypeOptions += "<option value='" + data[i].code + "'>" + data[i].name + "</option>";
                }
                subjectTypeSelection.append($(subjectTypeOptions));
            }
        },
        error: function (data) {
            toastr.warning(data.responseJSON.error);
        }
    });

    //open modal to create a new subject
    $('.create-subject-btn').click(function () {
        $('#subject-creation-modal').modal('show');
    });

    //create a subject
    $('#create-subject-btn').click(function () {
        //lấy dữ liệu từ form
        const formSubjectData = $('#create-subject-form').serializeArray();
        if (!formSubjectData || formSubjectData.length === 0) {
            return;
        }
        //chuyển dữ liệu từ object sang json
        const subjectRequestBody = {};
        for (let i = 0; i < formSubjectData.length; i++) {
            subjectRequestBody[formSubjectData[i].name] = formSubjectData[i].value;
        }
        //call api lên backend
        $.ajax({
            url: "/subjects",
            type: "POST",
            data: JSON.stringify(subjectRequestBody),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Create a subject successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (error) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //open modal to update a subject
    $('.update-subject-modal-open').click(function (event) {
        //call api lên java để lấy dữ liệu
        updateSubjectId = parseInt($(event.currentTarget).attr("subject-id"));
        $.ajax({
            url: "/subjects/" + updateSubjectId,
            type: "GET",
            success: function (data) {
                //đổ dữ liệu vào form
                $('#update-subject-form #subjectName').val(data.subjectName);
                $('#update-subject-form #credit').val(data.credit);
                $('#update-subject-form #subjectType').val(data.subjectType);

                $('#subject-update-modal').modal("show");
            },
            error: function (err) {
                console.error(err);
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //update a subject
    $('#update-subject-btn').click(function () {
        //lấy dữ liệu từ form
        const formSubjectData = $('#update-subject-form').serializeArray();
        if (!formSubjectData || formSubjectData.length === 0) {
            return;
        }
        //chuyển dữ liệu từ object sang json
        const subjectRequestBody = {};
        for (let i = 0; i < formSubjectData.length; i++) {
            subjectRequestBody[formSubjectData[i].name] = formSubjectData[i].value;
        }
        //call api lên backend
        $.ajax({
            url: "/subjects/" + updateSubjectId,
            type: "PUT",
            data: JSON.stringify(subjectRequestBody),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Update a subject successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (error) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //show modal to delete a subject
    $('.delete-subject-btn').click(function (event) {
        deleteSubjectId = parseInt($(event.currentTarget).attr("subject-id"));
        $('#subject-delete-modal').modal('show');
    });

    //delete a subject
    $('#delete-subject-btn').click(function () {
        $.ajax({
            url: "/subjects/" + deleteSubjectId,
            type: "DELETE",
            success: function (data) {
                toastr.success("Delete a subject successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });




});