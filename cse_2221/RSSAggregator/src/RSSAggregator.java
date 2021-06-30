import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert a list of RSS feeds into a website to direct to each rss
 * feed's news.
 *
 * @author Cameron Erdman
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int ret = -1; //sets return value to -1 incase the child isn't found
        int i = 0;
        //loops until the tag is found or no more children to search
        while (ret < 0 && i < xml.numberOfChildren()) {
            String name = xml.child(i).label();
            if (tag.equals(name)) { //if childs tag equals the searched for tag
                ret = i; //set ret to the index of that child
            }
            i++;
        }
        return ret; //returns the index of the first child with tag
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<tr>");
        int linkID = getChildElement(item, "link");
        int pubID = getChildElement(item, "pubDate");
        //if pub ID could be found prints it, else print no date available
        if (pubID != -1) {
            out.println("<td>" + item.child(pubID).child(0).label() + "</td>");
        } else {
            out.println("<td> No date available </td>");
        }

        int sourceID = getChildElement(item, "source");
        //prints the source id if it coulf be found, else print no source available
        if (sourceID != -1) {
            out.println("<td> <a href = \""
                    + item.child(sourceID).attributeValue("url") + "\">"
                    + item.child(sourceID).child(0).label() + "</a> </td>");
        } else {
            out.println("<td> No Source Available </td>");
        }
        //various checks to determine which to print
        int titleID = getChildElement(item, "title");
        int descriptionID = getChildElement(item, "description");
        //if the title is available print it, checks that title and link have children
        if (titleID != -1 && item.child(titleID).numberOfChildren() > 0
                && !item.child(titleID).child(0).isTag()
                && item.child(linkID).numberOfChildren() >= 0) {
            out.println("<td> <a href = \""
                    + item.child(linkID).child(0).label() + "\">"
                    + item.child(titleID).child(0).label() + "</a> </td>");
            //else print the description if its available
        } else if (descriptionID != -1
                && !item.child(descriptionID).child(0).isTag()) {
            out.println(
                    "<td> <a href = \"" + item.child(linkID).child(0).label()
                            + "\">" + item.child(descriptionID).child(0).label()
                            + "</a> </td>");
            //else print only the link if it is available and no title or description
        } else if (linkID != -1) {
            out.println(
                    "<td> <a href = \"" + item.child(linkID).child(0).label()
                            + "> No title available </a> </td>");
            //else print no title available
        } else {
            out.println("<td> No title available </td>");
        }
        out.println("</tr>");

    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param xml
     *            The XML item to draw from
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void indexFile(XMLTree xml, SimpleWriter out) {

        SimpleWriter htmlOut = new SimpleWriter1L("index.html");

        //finds Title and assigns it to head
        if (xml.hasAttribute("title")) {
            htmlOut.println("<html> <head> <title> "
                    + xml.attributeValue("title") + "</title>");
        } else {
            htmlOut.println("<html> <head> <title> Empty Title </title>");
        }
        htmlOut.println("</head> <body>");
//prints the title as the page h1
        if (xml.hasAttribute("title")) {
            htmlOut.println("<h1>" + xml.attributeValue("title") + " </h1>");
        } else {
            htmlOut.println("<h1> Empty Title </h1>");
        }
//prints the remaining tags
        htmlOut.println("<ul>");
//middle
        //loops from first case of item to last
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).hasAttribute("file")
                    && xml.child(i).hasAttribute("name")) {
                htmlOut.println("<li> <a  href = \""
                        + xml.child(i).attributeValue("file") + "\">"
                        + xml.child(i).attributeValue("name") + "</a> </li>");
            } else {
                htmlOut.println("<li> Improper news feed</li>");
            }
        }
//end
        //prints the closing tags
        htmlOut.println("</ul>");
        htmlOut.println("</body> </html>");
        htmlOut.close();
    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {

        SimpleWriter htmlOut = new SimpleWriter1L(file);

        XMLTree xml = checkRSS(url, out);
        XMLTree channel = xml.child(0);

        //finds the channel and prints the associated required tags
        int channelID = getChildElement(channel, "title");
        if (channelID != -1
                && channel.child(channelID).numberOfChildren() > 0) {
            htmlOut.println("<html> <head> <title> "
                    + channel.child(channelID).child(0).label() + "</title>");
        } else {
            htmlOut.println("<html> <head> <title> Empty Title </title>");
        }
        htmlOut.println("</head> <body>");
//finds the link to the news site and prints the tags with it
        int linkID = getChildElement(channel, "link");
        htmlOut.println("<h1> <a href= \""
                + channel.child(linkID).child(0).label() + "\"> "
                + channel.child(channelID).child(0).label() + " </a></h1>");
//prints the description as well as the remaining tags
        int descriptionID = getChildElement(channel, "description");
        if (descriptionID != -1
                && channel.child(descriptionID).numberOfChildren() > 0) {
            htmlOut.println("<p>"
                    + channel.child(descriptionID).child(0).label() + "</p>");
        } else {
            htmlOut.println("<p> No Description </p>");
        }
        htmlOut.println("<table border=\"1\">");
        htmlOut.println("<tr>");
        htmlOut.println("<th>Date</th>");
        htmlOut.println("<th>Source</th>");
        htmlOut.println("<th>News</th>");
        htmlOut.println("</tr>");

//middle
        //loops from first case of item to last
        for (int i = getChildElement(xml.child(0), "item"); i < xml.child(0)
                .numberOfChildren(); i++) {
            processItem(xml.child(0).child(i), htmlOut);
        }
//end
        //prints the closing tags
        htmlOut.println("</table>");
        htmlOut.println("</body> </html>");
    }

    /**
     * Asks for an rss link and checks to ensure it is a valid version 2.0 and
     * returns the xml item if it is.
     *
     * @param in
     *            the input stream
     *
     * @param out
     *            the output stream
     *
     * @return returns a valid xml item
     */
    private static XMLTree checkRSSList(SimpleReader in, SimpleWriter out) {
        int x = 0;
        XMLTree xml = null;
        while (x != 1) {
            out.print("Enter the name of your RSS list of links: ");
            String rss = in.nextLine();
            xml = new XMLTree1(rss); //grabs the tree and cheks if its a list of links
            if (xml.isTag() && xml.numberOfChildren() > 0
                    && xml.label().equals("feeds")
                    && xml.child(0).label().equals("feed")
                    && xml.child(0).hasAttribute("url")
                    && xml.child(0).hasAttribute("name")
                    && xml.child(0).hasAttribute("file")) {
                x = 1;
            } else {
                out.println(
                        "Sorry, this is not the link to the list we are looking for");
            }
        } //loops until a valid link is provided and returns it
        return xml;

    }

    /**
     * Asks for an rss link and checks to ensure it is a valid version 2.0 and
     * returns the xml item if it is.
     *
     * @param url
     *            the link to the RSS feed
     *
     * @param out
     *            the output stream
     *
     * @return returns a valid xml item
     */
    private static XMLTree checkRSS(String url, SimpleWriter out) {

        XMLTree xml = null;
        String rss = url;
        xml = new XMLTree1(rss); //grabs the tree and cheks if its rss 2.0
        if (!(xml.isTag() && xml.label().equals("rss")
                && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0"))) {
            out.println("Sorry, " + url + " is not a valid rss link");
        }
        //loops until a valid link is provided and returns it
        return xml;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        XMLTree list = checkRSSList(in, out);
        indexFile(list, out);
        for (int i = 0; i < list.numberOfChildren(); i++) {
            String url = list.child(i).attributeValue("url");
            String file = list.child(i).attributeValue("file");
            processFeed(url, file, out);
        }

        in.close();
        out.close();
    }

}
