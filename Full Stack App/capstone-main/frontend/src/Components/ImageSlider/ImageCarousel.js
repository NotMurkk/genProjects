
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";


import image1 from '../../images/katherine-conrad-QL3SaEwio_k-unsplash.jpg'
import image2 from '../../images/thebrewery.jpg';
import image3 from '../../images/OBC+grand+opening--+Leigh+Lofgren--+45.jpg'
import './ImageCarousel.css'
function ImgCarousel(){
    return(
        <div>
             <Carousel className='main-slide' autoPlay emulateTouch infiniteLoop >
                <div>
                    <img src={image1} height="690px" width="3px"/>
                </div>
                <div>
                    <img src={image2} height="690px" width="200px" />
                </div>
                <div>
                    <img src={image3} height="690px" width="200px"  />
                </div>
            </Carousel>
        </div>
    )
}
export default ImgCarousel;