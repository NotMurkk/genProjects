BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS beer CASCADE;
DROP SEQUENCE IF EXISTS seq_beer_id;
DROP TABLE IF EXISTS brewery CASCADE;
DROP SEQUENCE IF EXISTS seq_brewery_id;
DROP TABLE IF EXISTS review CASCADE;
DROP SEQUENCE IF EXISTS seq_review_id;
DROP TABLE IF EXISTS news_events CASCADE;
DROP SEQUENCE IF EXISTS seq_news_events_id;
DROP TABLE IF EXISTS favorite_brewery CASCADE;
DROP TABLE IF EXISTS favorite_beer CASCADE;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_beer_id
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE seq_brewery_id
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE seq_review_id
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE seq_news_events_id
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE beer (
	beer_id int DEFAULT nextval('seq_beer_id'::regclass) NOT NULL,
	brewery_id int NOT NULL,
	name varchar(50) NOT NULL,
	type varchar(50) NOT NULL,
	abv decimal(3,1) NOT NULL,
	description varchar(1000) NOT NULL,
	CONSTRAINT PK_beer PRIMARY KEY (beer_id)
);

CREATE TABLE brewery (
	brewery_id int DEFAULT nextval('seq_brewery_id'::regclass) NOT NULL,
	owner_id int NOT NULL,
	name varchar(50) NOT NULL,
	address varchar(50) NOT NULL,
	description varchar(1000),
	CONSTRAINT PK_brewery PRIMARY KEY (brewery_id)
);

CREATE TABLE review (
	review_id int DEFAULT nextval('seq_review_id'::regclass),
	beer_id int NOT NULL,
	author varchar(50) NOT NULL,
	rating int NOT NULL,
	review varchar(200),
	CONSTRAINT PK_review PRIMARY KEY (review_id)
);

CREATE TABLE news_events (
	news_events_id int DEFAULT nextval('seq_news_events_id'),
	brewery_id int NOT NULL,
	event_name varchar(50) NOT NULL,
	event_date DATE NOT NULL,
	event_description varchar(200),
	CONSTRAINT PK_news_events PRIMARY KEY (news_events_id)
);

CREATE TABLE favorite_brewery (
	user_id int NOT NULL,
	brewery_id int NOT NULL,
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_brewery_id FOREIGN KEY (brewery_id) REFERENCES brewery(brewery_id)
);

CREATE TABLE favorite_beer (
	user_id int NOT NULL,
	beer_id int NOT NULL,
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_brewery_id FOREIGN KEY (beer_id) REFERENCES beer(beer_id)
);

ALTER TABLE beer
ADD CONSTRAINT FK_brewery_id 
FOREIGN KEY (brewery_id) REFERENCES brewery(brewery_id);

ALTER TABLE brewery
ADD CONSTRAINT FK_owner_id 
FOREIGN KEY (owner_id) REFERENCES users(user_id);

ALTER TABLE review
ADD CONSTRAINT FK_beer_id 
FOREIGN KEY (beer_id) REFERENCES beer(beer_id);

ALTER TABLE news_events
ADD CONSTRAINT FK_brewery_id 
FOREIGN KEY (brewery_id) REFERENCES brewery(brewery_id);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

--Breweries
INSERT INTO brewery (owner_id, name, address, description) VALUES (2, 'Sinistral Brewing Co.', '123 North Street', 'We are a 5 barrel brewery, dedicated to crafting exceptional beers. Direct from owner and head brewer Blane Perry''s passion for the art of brewing, we offer a selection of signature beers, rotating seasonal specialties and experimental beers using locally sourced ingredients. Flight tastings, pints and crowler fills are available in our tasting room.');
INSERT INTO brewery (owner_id, name, address, description) VALUES (2, 'Eavesdrop Brewery', '456 South Ave.', 'Eavesdrop Brewery craft beers are a unique mix of local ingredients, fresh flavors, and new twists on classic beers. We take pride in what we brew and are always thinking outside the box!');
INSERT INTO brewery (owner_id, name, address, description) VALUES (2, 'Tucked Away Brewing Co.', '789 East Blvd.', 'Tucked Away Brewing Company is a Veteran-owned craft brewery in the City of Manassas founded by beer enthusiasts who turned their passion for home brewing into a full-scale brewery. The focus for each batch is to make and share amazing beer. The name says it all, it is Tucked Away, but as the owners learned through their travels, along every journey are the great tucked away places that make it worth it!');
INSERT INTO brewery (owner_id, name, address, description) VALUES (2, '2 Silos Brewing Co.', '987 West Rd.', 'From lagers to barrel-aged stouts, we at 2 Silos Brewing Co. pride ourselves on creating high-quality, delicious craft beer offerings while respecting our environment and playing an active role in our community.Located on the campus of Farm Brew LIVE at Innovation Park in Prince William County, VA, we are a premier destination brewery. A place for family and friends to come together and enjoy craft beer, food, cocktails, and live music.  We’re a family committed to bringing the best that local brewers, chefs, and entertainers have to offer in one unforgettable experience.');

--Beer
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Summerteeth Hard Seltzer', 'Hard Seltzer', 4.6, 'If beer''s not your thing, you''ll love our hard seltzer. Enjoy it straight from the tap either unflavored or with Pineapple or Prickly Pear Key Lime.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Summerteeth Frozen Seltzer', 'Hard Seltzer', 5.0, 'Summerteeth Seltzer, a collab with Ocelot, is a frozen, daquiri-style hard seltzer that will cool you off in the summertime heat. This weeks flavor(s): Strawberry and Pear.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Fish Taco Lager at the Beach', 'Lager', 4.2, 'Fish Taco Lager but make it beachy! We added key lime puree to our famous Fish Taco lager to help you count down the days to summertime!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'El Borracho Burro Dark Lager', 'Lager', 5.4, 'Something magical happens when the Mexican and American cultures combine—Tex-Mex, Norteño, Lucha Libre and now El Borrocho Burro Dark Lager inspired by the El Borrocho Burro bar in Isla Mujeres, Mexico. Just like burritos, La Bamba and masked wrestlers, this dark copper Mexican lager has the best of all worlds, featuring Vienna, Pilsner, Munich, Carafa, and Chocolate malts for a rich toasted bread crust start with a hint of chocolate and caramel. This ecru-headed beer then finishes dry and malty with a restrained bitterness provided by the Hallertauer and Tettnang hops. Sure, it might translate to the Drunken Ass, but that''s what it takes in the form of Mark Dixon and Blane Perry to produce this spot on take on a Mexican lager. Like all Sinistral beers this one is true to its influences but combines north and south of the border to produce a brew worthy of Enchiladas, Nacho Libre and Los Lobos.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'King Kamehameha Apricot Wheat', 'Wheat Beer', 5.3, 'King Kamehameha Wheat is an American Pale Wheat beer that combines 2-Row and White Wheat to form a deliciously crushable beer. “King K” Apricot Wheat is our latest version of this beer, out just in time for spring.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Sweet Sassy, My Lassi', 'Sour', 6.8, 'We teamed up with our friends from Mustang Sally and Public House Brewery and Kitchen to bring you Sweet Sassy, My Lassi! It''s a lightly soured brew that opens with cardamom and mango, delivers soft notes of vanilla and cream and finishes with a light dry sweetness. Brewed with Pilsner, Vienna and Acidulated malts this 6.8% ABV snort is inspired by the tart Indian beverage Lassi and is sassy like your favorite 90s teen magazine. As with all Sinistral beers Sweet Sassy My Lassi is true to its inspiration and “You don''t have to get out the checkbook to pay grandma for the rubdown!”');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Soft Sip IPA', 'IPA', 6.5, 'Sometimes you just want a light body IPA that is bone dry, hop forward, with bright notes of Pineapple and Mango. No, it''s not a Brut. Made with Pale, Wheat, Victory malts, hopped with Centennial, Amarillo and Citra, the Soft Sip IPA is just what you need. Coming in at 6.5% ABV and around IBU, this refreshingly crisp beer will be a crusher.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Er'' Day IPA', 'IPA', 7.0, 'Every day I dream about a golden, West Coast IPA hopped up on lots of Citra, Mosaic, Nugget and Simcoe hops. One with a rich maltiness from Pale, Malted Oats, and Biscuit malts to stand up to and even out that hop bomb. Maybe one that delivers 7.0% AVB of goodness along some piny-ness and maybe a little tropical fruit. With Er''day IPA I have to dream no longer and can simply head to Sinistral every day to enjoy my dream beer. Like all Sinistral beers Er''Day is true to its influences and what dreams are made of.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Falily of Trees DIPA', 'IPA', 8.9, 'A collaboration with our dearest of friends at Ocelot Brewing Company, Family of Trees is a bicoastal new school throwback Double IPA. Or something like that. This stealthy beast was brought to life with a two-row barley base, plus a generous addition of English pale malt and malted rye. We hopped it with Centennial in the kettle, then Simcoe and more Centennial during whirlpool, and finally Simcoe and Azacca after primary fermentation. In the end, Family of Trees is a little Northeast and a little West Coast. It''s juicy with a full body… but also a firm bitterness and dry finish. There''s apricot and peach, mango and pineapple, with undercurrents of red berry and pine sap.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Van Dang!', 'Belgian Strong Golden Ale', 9.1, 'When you think Belgium, you think strong as in the “Muscles from Brussels” and as in a robust golden ale. Though strong, the Van Dang! opens with an effervescent nose featuring hints of pear, but soon finds a hard target with a spicy note of alcohol that characterizes these Belgian brutes. Generous hopping with Willamette, Mt. Hood and Northern Brewer hops provides a triple impact. Brewing with Pilsner, Caravienne, and Acidulated malts and beet sugar may be a maximum risk, but its 9.1% ABV and dry finish are no death warrant. Like all Sinistral beers Van Dang! Golden Strong Ale is true to its influences and so Belgian it leaves even JCVD nowhere to run.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Arden''s Amber Ale', 'Amber', 5.0, 'Just like the little girl this beer is a tribute to, you won''t be able to stop smiling once you''ve tasted our A3, which is made using a combination of pale and crystal malt and gives this beer a malty finish with subtle notes of caramel and toffee.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (1, 'Heavy Rider Barrel Aged Stour', 'Stout', 9.0, 'Our Heavy Rider stout aged in Buffalo Trace barrels for a year. Brewed with Maris, Crystal 80, Crystal 120, Chocolate, Wheat, flaked oats and Carafa Malts resulting in a very smooth stout. Heavy Rider packs a nice chocolate flavor with notes of caramel and a touch of booziness. Coming in at 9% ABV, enjoy this one responsibly.');

INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'White Winged Dove', 'Wheat Beer', 4.9, 'This Belgian-style witbier is spiced with coriander and orange peel. Originally brewed and named by our female staff, friends, and family in celebration of International Women''s Day.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Bird Flipper', 'Lager', 5.5, 'Amber lager made for wearing crocs and mowing lawns. Toasty malt, noble hops, and yeast from Munich''s oldest independently owned brewery.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Socorro Lime Lager', 'Lager', 4.8, 'Crushable light lager infused with lime zest.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Borealis Pils', 'Pilsner', 4.7, 'Lightly toasty malt flavor balanced with spicy and floral Saaz hops makes this a uniquely crisp lager.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Loft Kolsch', 'Kolsch', 4.8, 'Light and drinkable German-style ale with floral hops and a dry crisp finish.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, '3 Pigeons Walk Into A Bar', 'Sour', 7.0, 'First in our line of cocktail-esque sours. Classic Amaretto sour flavors of almond, citrus, & Maraschino cherry.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Lip Reader', 'Sour', 3.3, 'Collaboration with Charm City Meadworks. Traditional sour wheat beer with added Dark Miambo and Poly Floral honey. Finishes floral, fruity, and tart.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'The Schmidt', 'Lager', 5.1, 'This collaboration with Jasper Yeast showcases an American pre-prohibition lager yeast paired with a classic Munich Dunkel. Dark, smooth, and toasty.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'The Yorkshire', 'IPA', 7.0, 'The beer that put us on the map... Huge citrus & tropical hop aroma, soft mouthfeel, low bitterness. Worthy of naming after our locale.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Alycious', 'IPA', 7.6, 'Alycious, named after one of our own team members, is a beautifully hazy. Citrus, mango, pineapple, and stone fruit smack you right in the face.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Grumpy Old Bird', 'Stout', 4.3, 'Dry Irish Stout with a healthy dose of grumpiness. Sláinte!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (2, 'Arch Nemesis', 'Stout', 9.5, 'Strong chocolate with hints of coffee. Milk sugar adds velvety mouth-feel. Served on nitro.');

INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Squadron Commander', 'English Bitter', 5.5, 'This classic Extra Special Bitter Ale (ESB) is a crowd favorite. Squadron Commander is a delicious, drinkable British pub standard. Not overly bitter, it balances malty, biscuity flavor with minimal/moderate hop bitterness from Willamette hops. FLY HIGH BUT NEVER BREAK FORMATION');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Uniconrs & Hand Grenades', 'IPA', 8.1, 'Late one evening while cleaning up after a long brew day, we heard a strange sound. We looked out the back of the brewery and saw a beautiful unicorn carrying eight hand grenades. We watched with awe as the unicorn danced across the sky, frolicking amongst the stars. At that moment Unicorns and Hand Grenades was born. This Imperial Sour IPA tickles the tastebuds with a tart and fruity magical experience. Pull the pin and throw this hand grenade for an explosion of sour pineapple notes and aroma.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Balikatan Hops', 'IPA', 8.0, 'Named after the historic military treaty by the US & The Philippines. The “Balikatan” exercise (means shoulder-to-shoulder in Tagalog), a two week joint forces exercise. The annual reunion has been the cornerstone of the US-Philippines military relations since 1991. Balikatan hops is brewed with Elias and Tucked Away favorite hops, Idaho 7, Galaxy, Talus, and Nelson Sauvin. This beer delivers lots of tropical hop flavours like coconut, mango, and pineapple combined with great mouthfeel from the use of oats and wheat.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Sunshine And Daisy Cutters Creamsicle', 'IPA', 6.5, 'Creamsicle IPA collaboration with Tin Cannon Brewing Co');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Fix Bayonets', 'IPA', 7.4, 'This New England Style IPA is hazy, juicy, and far less stabby (bitter) than traditional IPAs. Its pineapple and grapefruit notes, with other tropical flavors, come from generous use of El Dorado, Mosaic, and Kohatu hops. (No added fruit) UNSHEATH THIS JUICY IPA!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Pop Smoke Cherrywood', 'Porter', 5.5, 'Pop smoke is a common military term for throwing a smoke grenade in order to provide concealment for infantry and other fighting forces or to mark a landing zone. Our Pop Smoke Cherrywood Porter has nothing to hide, but many things going on. It is a dark, roasty, nutty, chocolatey, and rich English porter with a light, smoky background and a hint of cherrywood fruitiness.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Not My Problem', 'IPA', 9.3, 'Not My Problem is a favorite saying of a military commander who was resolute in pushing his subordinates to succeed on their own. This IPA is an adaptation and expansion of a classic West Coast IPA, with several specialty malts and strong Centennial, Mosaic and Citra hops. It is moderately strong, moderately bitter, and slightly malty with lovely classic Citra hop aroma.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Fox Four', 'Wheat Beer', 5.5, '"Fox Four!" or "Guns, Guns, Guns!" is the fighter jet radio call when a pilot is about to unleash the automatic machine guns on an enemy jet. Fox Four Hefeweizen is a barrage of flavors on your taste buds. It is partnered with Strawberry to provide an enjoyable beer all the way to the bottom of the glass. GIVE''ER THE GUNS!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Pace Count', 'Pilsner', 5.5, 'In the military, one way of measuring distance is by using a Pace Count: the number of steps taken per 100 meters. No matter how you choose to measure your progress in life, you can always count on this easy-drinking German-style Pilsner to be crisp and refreshing. ENJOY THE JOURNEY, PACE YOURSELF');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (3, 'Blue Force Tracker', 'IPA', 6.4, 'This IPA celebrates a symphony of hops. Maltiness fades to the background as aromas of citrus, floral, pine, tropical and stone fruits are intertwined throughout every sip. BE BRAVE TAKE RISKS KNOW YOUR ALLIES');

INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'Hefe', 'Wheat Beer', 5.4, 'Our golden-straw colored wheat beer has spice and clove aromas with a subtle banana after taste. This full bodied Ale is a refreshing way to close-out your summer vacation. Best enjoyed fresh!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'Citralicious', 'IPA', 6.8, 'This American IPA is brewed with whole tangerine puree and generously dry-hopped with Citra and Mosaic hops to enhance the delicious tropical flavors and aromas. With a crisp, bright finish, it''s Best enjoyed fresh!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'Mosaic Goat', 'IPA', 7.2, 'This Double IPA was crafted to showcase a sticky blend of Cashmere, El Dorado, and Mosaic hops. Double dry-hopped to enhance the already pungent aromas, taste a balance of over-ripened tropical fruit, citrus, & stone fruit with a slightly sweet finish.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'Squared Pants Pineapple Guava Sour', 'Sour', 4.7, 'A refreshingly tart fruited sour blended with natural Pineapple & Guava purees. A tasty tropical oasis in every pour!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'Stereophonic Tracks', 'IPA', 7.5, 'Our resinous old-school IPA is packed full of Cascade, Centennial and Simcoe hops to create a bold throwback blend of piney and citrus flavors. This classic IPA has been dry hopped to enhance the hop aromatics and complement the delicious malt backbone. Best Enjoyed Fresh!');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'White Belgian', 'Wheat Beer', 5.6, 'This seasonal treat is cleverly spiced with coriander, grains of paradise, and orange peel, producing a refreshingly light and zesty ale.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'Dos Silos Cerveza', 'Lager', 5.3, 'An authentic Mexican Lager, our Dos Silos was crafted using corn and other ingredients frequently used by breweries in Mexico. Fermented at low temperatures and cold conditioned for months to create a crisp, light, and refreshing lager.');
INSERT INTO beer (brewery_id, name, type, abv, description) VALUES (4, 'Cold Boi', 'Lager', 5.2, 'A tasty cold boi based on a traditional Maibock. This crushable malty golden lager is here to welcome longer days and warmer weather.');

--Events
INSERT INTO news_events (brewery_id, event_name, event_date, event_description) VALUES (1, 'Bingo Night', '2022-06-01', 'Every Wednesdy night from 9pm-close is BINGO NIGHT! Bring a friend, grab a beer, and try to win gift card to many local stores and restaurants.');
INSERT INTO news_events (brewery_id, event_name, event_date, event_description) VALUES (1, 'Trivia Night', '2022-06-02', 'Every Thursday night from 9pm-close is TRIVIA NIGHT! Bring a friend, grab a beer, and try to win gift card to many local stores and restaurants.');
INSERT INTO news_events (brewery_id, event_name, event_date, event_description) VALUES (1, 'Food Truck Week', '2022-06-06', 'All week we will have a new food truck on site each day.');
INSERT INTO news_events (brewery_id, event_name, event_date, event_description) VALUES (1, '50% Off Pint Glasses', '2022-06-10', 'Come in and get yourself a Twin Barrel pint glass for half price.');

--Reviews
INSERT INTO review (beer_id, author, rating, review) VALUES (1, 'Michele', 4, 'I''m not a big fan of beer and this is a great alternative.');
INSERT INTO review (beer_id, author, rating, review) VALUES (1, 'Brandon', 3, 'Decent summer drink.');
INSERT INTO review (beer_id, author, rating, review) VALUES (2, 'Daniel', 4, 'Hits the spot on a hot day');
INSERT INTO review (beer_id, author, rating, review) VALUES (2, 'David', 5, 'Great summer drink');
INSERT INTO review (beer_id, author, rating, review) VALUES (3, 'Larry', 3, 'Tastes better with a lime');
INSERT INTO review (beer_id, author, rating, review) VALUES (3, 'David', 5, 'I didn''t think it would tase good by the name but it was suprisingly delicious');
INSERT INTO review (beer_id, author, rating, review) VALUES (4, 'Cory', 4, 'Lighter tasting than it looks.');
INSERT INTO review (beer_id, author, rating, review) VALUES (4, 'Justin', 3, 'Nice dark lager');
INSERT INTO review (beer_id, author, rating, review) VALUES (5, 'Ray', 3, 'Some of the other flavors are better but this is subtle which is nice.');
INSERT INTO review (beer_id, author, rating, review) VALUES (5, 'Larry', 4, 'Nice hint of apricot.');
INSERT INTO review (beer_id, author, rating, review) VALUES (6, 'Jennifer', 5, 'Perfect mango flavor');
INSERT INTO review (beer_id, author, rating, review) VALUES (6, 'Michelle', 2, 'Way too sour.');
INSERT INTO review (beer_id, author, rating, review) VALUES (7, 'Mark', 4, 'Great for trivia night.');
INSERT INTO review (beer_id, author, rating, review) VALUES (7, 'Craig', 5, 'This might be my new all-time favorite.');
INSERT INTO review (beer_id, author, rating, review) VALUES (8, 'Mark', 4, 'The perfect beer on a tuesday');
INSERT INTO review (beer_id, author, rating, review) VALUES (8, 'Fred', 3, 'More bitter than expected');
INSERT INTO review (beer_id, author, rating, review) VALUES (9, 'Bob', 4, 'I can taste the Ocelot influence.');
INSERT INTO review (beer_id, author, rating, review) VALUES (9, 'David', 4, 'Nice fruit flavor');
INSERT INTO review (beer_id, author, rating, review) VALUES (10, 'Larry', 4, 'Very good');
INSERT INTO review (beer_id, author, rating, review) VALUES (10, 'Wes', 2, 'Too strong!');
INSERT INTO review (beer_id, author, rating, review) VALUES (11, 'Chris', 3, 'Hoppy, bitter, astringent, malty. A good red. Tastes great, not filling.');
INSERT INTO review (beer_id, author, rating, review) VALUES (11, 'Sean', 3, 'Very good red Ale');
INSERT INTO review (beer_id, author, rating, review) VALUES (12, 'Jeff', 4, 'THATS got a LOT going on.');
INSERT INTO review (beer_id, author, rating, review) VALUES (12, 'Kelley', 4, 'Great for date night');

INSERT INTO review (beer_id, author, rating, review) VALUES (13, 'Bobby', 4, 'Nice traditional wheat beer. Good coriander and orange peel.');
INSERT INTO review (beer_id, author, rating, review) VALUES (13, 'Tim', 3, 'Nice easy drinking wheaty brew');
INSERT INTO review (beer_id, author, rating, review) VALUES (14, 'Ben', 4, 'A little less malt flavor than I''d like in an amber, but still really good.');
INSERT INTO review (beer_id, author, rating, review) VALUES (15, 'Jay', 4, 'Nice, easy sipping on a summer day beer. Lime is very apparent in the best way.');
INSERT INTO review (beer_id, author, rating, review) VALUES (15, 'Tim', 4, 'Nice light refreshing lime lager. Perfect for hot day on the porch or beach.');
INSERT INTO review (beer_id, author, rating, review) VALUES (16, 'Thomas', 4, 'Nice clean crisp pilsner');
INSERT INTO review (beer_id, author, rating, review) VALUES (16, 'Tanner', 4, 'Slammable. Light. Dusty.');
INSERT INTO review (beer_id, author, rating, review) VALUES (17, 'Tom', 4, 'Nice kolsch on a summer day. Would have again.');
INSERT INTO review (beer_id, author, rating, review) VALUES (17, 'Tim', 3, 'Crisp light kolsch.');
INSERT INTO review (beer_id, author, rating, review) VALUES (18, 'Lisa', 4, 'Quite tasty!!');
INSERT INTO review (beer_id, author, rating, review) VALUES (18, 'Bailey', 3, 'Not bad but definitely more of a cider/cocktail than a beer.');
INSERT INTO review (beer_id, author, rating, review) VALUES (19, 'Liz', 4, 'Not very sour.');
INSERT INTO review (beer_id, author, rating, review) VALUES (20, 'Greg', 3, 'Smooth malt flavor with a nice bite and bitter finish.');
INSERT INTO review (beer_id, author, rating, review) VALUES (20, 'Jeff', 3, 'Malty, crisp, pleasant.');
INSERT INTO review (beer_id, author, rating, review) VALUES (21, 'Bailey', 4, 'No crazy harsh bite.');
INSERT INTO review (beer_id, author, rating, review) VALUES (21, 'Jeff', 3, 'Great beer.');
INSERT INTO review (beer_id, author, rating, review) VALUES (22, 'Greg', 4, 'Solid. Nice hoppy flavor with a decent citrus combo.');
INSERT INTO review (beer_id, author, rating, review) VALUES (22, 'Mike', 5, 'Hoppy deliciouness!');
INSERT INTO review (beer_id, author, rating, review) VALUES (23, 'Greg', 2, 'Roasted, then roasted finish.');
INSERT INTO review (beer_id, author, rating, review) VALUES (23, 'Fred', 4, 'Great beer for a cold day.');
INSERT INTO review (beer_id, author, rating, review) VALUES (24, 'Michael', 3, 'Eh.');
INSERT INTO review (beer_id, author, rating, review) VALUES (24, 'Liam', 2, 'A little too bitter without a great finish.');

COMMIT TRANSACTION;
