; Wait for the file upload window to become active (adjust title if needed)
WinWaitActive("www.ilovepdf.com wants to open", "", 10)

; Focus on the "File name" input field (usually "Edit1")
ControlFocus("www.ilovepdf.com wants to open", "", "Edit1")

; Set the full path of the file to be uploaded
ControlSetText("www.ilovepdf.com wants to open", "", "Edit1", "C:\Users\car\Downloads\SeleniumGrid.pdf")

; Click the "Open" button (usually "Button1")
ControlClick("www.ilovepdf.com wants to open", "", "Button1")
