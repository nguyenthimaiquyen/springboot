$(document).ready(function () {

    toastr.options.timeOut = 2500; // 2.5s

    let deleteSubjectId = -1;

    // get subject types
    $.ajax({
        url: "/subjects/type",
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            const subjectTypeSelection = $('#subject-modal #subjectType');
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

    $.validator.addMethod("creditCustom", function (value, element) {
        if (this.optional(element)) {
            return true;
        }
        console.log(value)
        console.log(element)
        return this.optional(element) || value > 0;
    }, "Credit must be greater than zero");

    //validate
    const validator = $('#subject-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            'subjectName': {
                required: true,
                maxlength: 100
            },
            'credit': {
                required: true,
                digits: true,
                creditCustom: true
            },
            "subjectType": {
                required: true
            }
        },
        messages: {
            'subjectName': {
                required: "Subject name is required",
                maxlength: "Subject name must be less than 100 characters"
            },
            'credit': {
                required: "Credit is required",
                digits: "Credit must be digits",
                creditCustom: "Credit must be greater than zero"
            },
            "subjectType": {
                required: "Subject type is required"
            }
        }
    });

    //open modal to create a new subject
    $('.create-subject-btn').click(function () {
        $('#subject-modal #save-subject-btn').attr("action-type", "CREATE");
        $('#subject-modal').modal('show');
    });

    //open modal to update a subject
    $('.update-subject-modal-open').click(async function (event) {
        $('#subject-form').trigger("reset");
        validator.resetForm();
        //call api lên java để lấy dữ liệu
        const updateSubjectId = parseInt($(event.currentTarget).attr("subject-id"));
        let subject = null;
        await $.ajax({
            url: "/subjects/" + updateSubjectId,
            type: "GET",
            success: function (data) {
                subject = data;
            },
            error: function (err) {
                toastr.warning(data.responseJSON.error);
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });

        if (!subject) {
            toastr.error("Đã có lỗi xảy ra, vui lòng thử lại!")
            return;
        }

        //đổ dữ liệu vào form
        $('#subject-form #subjectName').val(subject.subjectName);
        $('#subject-form #credit').val(subject.credit);
        $('#subject-form #subjectType').val(subject.subjectType);

        $('#subject-modal #save-subject-btn').attr('action-type', "UPDATE");
        $('#subject-modal #save-subject-btn').attr("subject-id", updateSubjectId);
        $('#subject-modal').modal("show");
    });

    //create or update a subject
    $('#save-subject-btn').click(function (event) {
        //validate
        const isValidForm = $('#subject-form').valid();
        if (!isValidForm) {
            return;
        }
        const actionType = $(event.currentTarget).attr("action-type");
        const subjectId = $(event.currentTarget).attr("subject-id");
        //lấy dữ liệu từ form
        const formSubjectData = $('#subject-form').serializeArray();
        if (!formSubjectData || formSubjectData.length === 0) {
            return;
        }
        //chuyển dữ liệu từ object sang json
        const subjectRequestBody = {};
        for (let i = 0; i < formSubjectData.length; i++) {
            subjectRequestBody[formSubjectData[i].name] = formSubjectData[i].value;
        }
        const method = actionType === "CREATE" ? "POST" : "PUT";
        if (method === "PUT") {
            subjectRequestBody["id"] = subjectId;
        }
        //call api lên backend
        $.ajax({
            url: "/subjects",
            type: method,
            data: JSON.stringify(subjectRequestBody),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new subject successfully!");
                $(event.currentTarget).attr("action-type", "");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (error) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
        $("#subject-modal #save-subject-btn").attr("action-type", "");
        $('#subject-modal #save-subject-btn').attr("subject-id", "");
        // $('#subject-form').trigger("reset");
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

    //reset form
    $('#subject-modal').on('hidden.bs.modal', function () {
        $("#subject-modal #save-subject-btn").attr("action-type", "");
        $('#subject-modal #save-subject-btn').attr("subject-id", "");
        $('#subject-form').trigger("reset");
        validator.resetForm();
    });
    //
    // // close modal -> clear form + reset form, delete action-type attribute at submit button
    // $(".close-modal").click(() => {
    //     $("#subject-modal #save-subject-btn").attr("action-type", "");
    //     $('#subject-modal #save-subject-btn').attr("subject-id", "");
    //     $('#subject-form').trigger("reset");
    //     validator.resetForm();
    // });

});