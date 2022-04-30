# flight-system-project-partsb-c
The continuation of flight-system-project-part-a

פרויקט מערכת טיסות
קורס JAVA
חלק ב' – REST CONTROLLERS

בניית צד השרת:

תיאור :
בשלב זה נבנה שרת API Web בשפת JAVA  אשר ייתן מענה לבקשות   HTTP אשר  יגיעו מהלקוח, ובהמשך ל- REQUESTלפרוייקט ה- WebAPI שתבנה יהיה קישור (reference )לפרוייקט מערכת לניהול טיסות <חלק א'>
ניתן למקם את כל הפרוייקטים ב PACKAGE אחד
השרת יאפשר גישה לפונקציות הנמצאות בכל אחד מה—FAÇADE
	-- AdministratorFacde – מנהל מערכת
	AirlineCompanyFacde – חברת תעופה
	CustomerFacde – לקוח )אחרי לוגין(
	-AnonymousFacade – לקוח אנונימי

יש לכתוב ApiController נפרד לכל Facade ולאכלס בתוכו את כל הפונקציות הנמצאות באותו .GET POST PUT DELETE קטגוריות לפי FACADE -ה לדוגמא: פונקצייה המחזירה רשימה של כרטיסים תקוטלג כ- GET ,פונקציה המוסיפה טיסה תקוטלג כ- POST וכו'
	יש לכתוב טסטים ל- WebApi באמצעות Client HTTPjava.כתוב טסט יחיד לכל FAÇADE) סה"כ 4 טסטים(


בניית צד השרת (2):

תיאור :
בשלב זה נבנה DB בעזרת המנגנון של JPA 
אתם צריכים לבנות DTO שמתאר טבלה שמכילה:
-messageId (PK) :int
-senderName : String
-content :String
יש לבנות סירבר וריפו וקונטרוליר של GET POST שיושב בלינק של 
"/contactus"

בניית צד השרת (3):

תיאור :
בשלב זה נוסיף את חלק האבטחה לפרוייקט 
	יש לדרוס את פונקציית ההרשאות ולתת הרשאות מתאימות
	AUTHORAIZAION ->        /ADMIN **    role "ADMIN"
	/customer/**  role "customer"
	airlineCompany/**   role "airline"/
	.פרימיטאלל /
***atgar Role -> Enums
