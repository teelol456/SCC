package lib;
import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เป็นเมธอดที่ไว้คำนวณราคาสินค้าในตะกร้าโดยใน 1 ตะกร้ามีได้หลายสินค้า 
     * และ 1 สิ้นค้าจะประกอบไปด้วยรหัสการซื้อ ชื่อสินค้า ราคาต่อชิ้น จำนวนที่ซื้อ
     * @whatif
     * - ถ้า items เป็น null หรือ empty จะไม่คำนวณ หรือคินค่า 0.0<p>
     * - ถ้า CartItem มี price หรือ quantity ติดลบ จะไม่คำนวณ หรือข้ามสินค้านั้น
     * @ราคารวมพื้นฐาน: คำนวณจาก price * quantity ของสินค้านั้น
     * @ราคารวมส่วนลด:
     * - ส่วนลด "BOGO": โปรโมชันซื้อ 1 แถม 1 (เช่น ซื้อ 2 จ่าย 1, ซื้อ 3 จ่าย 2, ซื้อ 4 จ่าย 2) <p>
     * - ส่วนลด "BULK": หากซื้อตั้งแต่ 6 ชิ้นขึ้นไป จะได้รับส่วนลด 10% จากราคารวมของสินค้านั้น
     * @param รับ AlleyList CartItem ที่มีสินค้าและช้อมูลของสินค้านั้น ประกอบไปด้วยรหัสการซื้อ ชื่อสินค้า ราคาต่อชิ้น จำนวนที่ซื้อ
     * @return ราคาสุทธิของสินค้าทั้งหมดใน CartItem โดยเป็นราคาที่รวมส่วนลด
     */
    
    public static double calculateTotalPrice(ArrayList<CartItem> items) {

        if (items==null || items.size() <= 0) {
            return 0.0;
        }
        double Total = 0.0;
         for (CartItem i : items) {
            if (i.price() <= 0 || i.quantity() <= 0){
                continue;
            }
            if(i.sku() == "NORMAL"){
                Total += i.price()*i.quantity();
            }
            else if(i.sku() == "BOGO"){
                Total += (i.price()*i.quantity()) - (i.price() * (Math.ceil(i.quantity()/2)));
            }
            else if(i.sku() == "BULK"){
                if (i.quantity() >= 6){
                    Total += i.price()*i.quantity()  - ((i.price() * i.quantity())/10);
                }
                else Total += i.price()*i.quantity();
            }
        }
        return Total;
    }
}