import BackgroundSlider from 'react-background-slider'

import image1 from '../../images/katherine-conrad-QL3SaEwio_k-unsplash.jpg'
import image2 from '../../images/dc-breweries-good-food-denizens-brewing-company_featured.webp';
import image3 from '../../images/OBC+grand+opening--+Leigh+Lofgren--+45.jpg'
import image4 from '../../images/right-proper-LEAD.jpg'
import image5 from '../../images/img1.jpg'
import image6 from '../../images/image2.jpg'
import image7 from '../../images/IMG_5085.webp'
function ImgSlider(){
    return(
        <BackgroundSlider
          images={[image5, image4, image7, image2, image3, image1, image6]}
          duration={4}
          transition={2}
        />
    )
}
export default ImgSlider