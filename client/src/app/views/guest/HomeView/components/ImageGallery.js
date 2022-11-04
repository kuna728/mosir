import React from "react";
import {Carousel} from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import swimming_image from "../../../../assets/images/home_gallery/image1.jpg";
import yoga_image from "../../../../assets/images/home_gallery/image2.jpg";
import table_tennis_image from "../../../../assets/images/home_gallery/image3.jpg";
import athletics_image from "../../../../assets/images/home_gallery/image4.jpg";
import gym_image from "../../../../assets/images/home_gallery/image5.jpg"
import fencing_image from "../../../../assets/images/home_gallery/image6.jpg"
import tennis_image from "../../../../assets/images/home_gallery/image7.jpg"
import climbing_image from "../../../../assets/images/home_gallery/image8.jpg"

export default function ImageGallery() {
    return (
        <Carousel>
            <div>
                <img src={swimming_image} alt="swimming"/>
                <p className="legend">Pływanie</p>
            </div>
            <div>
                <img src={yoga_image} alt="yoga"/>
                <p className="legend">Joga</p>
            </div>
            <div>
                <img src={table_tennis_image} alt="table_tennis"/>
                <p className="legend">Tenis stołowy</p>
            </div>
            <div>
                <img src={athletics_image} alt="athletics"/>
                <p className="legend">Lekkoatletyka</p>
            </div>
            <div>
                <img src={gym_image} alt="gym"/>
                <p className="legend">Siłownia</p>
            </div>
            <div>
                <img src={fencing_image} alt="fencing"/>
                <p className="legend">Szermierka</p>
            </div>
            <div>
                <img src={tennis_image} alt="tennis"/>
                <p className="legend">Tenis</p>
            </div>
            <div>
                <img src={climbing_image} alt="climbing"/>
                <p className="legend">Wspinaczka</p>
            </div>
        </Carousel>
    )
}
