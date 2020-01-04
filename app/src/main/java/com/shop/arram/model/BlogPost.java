package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogPost {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("date_gmt")
    @Expose
    public String dateGmt;
    @SerializedName("guid")
    @Expose
    public Guid guid;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("modified_gmt")
    @Expose
    public String modifiedGmt;

    @SerializedName("slug")
    @Expose
    public String slug;

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("link")
    @Expose
    public String link;

    @SerializedName("title")
    @Expose
    public Title title;

    @SerializedName("content")
    @Expose
    public Content content;

    @SerializedName("excerpt")
    @Expose
    public Excerpt excerpt;

    @SerializedName("author")
    @Expose
    public int author;
    @SerializedName("featured_media")
    @Expose
    public int featuredMedia;
    @SerializedName("comment_status")
    @Expose
    public String commentStatus;
    @SerializedName("ping_status")
    @Expose
    public String pingStatus;
    @SerializedName("sticky")
    @Expose
    public boolean sticky;
    @SerializedName("template")
    @Expose
    public String template;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("meta")
    @Expose
    public List<Object> meta = null;
    @SerializedName("categories")
    @Expose
    public List<Integer> categories = null;
    @SerializedName("tags")
    @Expose
    public List<Object> tags = null;
    @SerializedName("featured_image_src")
    @Expose
    public FeaturedImageSrc featuredImageSrc;
    @SerializedName("_links")
    @Expose
    public Links links;

    public BlogPost withId(int id) {
        this.id = id;
        return this;
    }

    public BlogPost withDate(String date) {
        this.date = date;
        return this;
    }

    public BlogPost withDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
        return this;
    }

    public BlogPost withGuid(Guid guid) {
        this.guid = guid;
        return this;
    }

    public BlogPost withModified(String modified) {
        this.modified = modified;
        return this;
    }

    public BlogPost withModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
        return this;
    }

    public BlogPost withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public BlogPost withStatus(String status) {
        this.status = status;
        return this;
    }

    public BlogPost withType(String type) {
        this.type = type;
        return this;
    }

    public BlogPost withLink(String link) {
        this.link = link;
        return this;
    }

    public BlogPost withTitle(Title title) {
        this.title = title;
        return this;
    }

    public BlogPost withContent(Content content) {
        this.content = content;
        return this;
    }

    public BlogPost withExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
        return this;
    }

    public BlogPost withAuthor(int author) {
        this.author = author;
        return this;
    }

    public BlogPost withFeaturedMedia(int featuredMedia) {
        this.featuredMedia = featuredMedia;
        return this;
    }

    public BlogPost withCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
        return this;
    }

    public BlogPost withPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
        return this;
    }

    public BlogPost withSticky(boolean sticky) {
        this.sticky = sticky;
        return this;
    }

    public BlogPost withTemplate(String template) {
        this.template = template;
        return this;
    }

    public BlogPost withFormat(String format) {
        this.format = format;
        return this;
    }

    public BlogPost withMeta(List<Object> meta) {
        this.meta = meta;
        return this;
    }

    public BlogPost withCategories(List<Integer> categories) {
        this.categories = categories;
        return this;
    }

    public BlogPost withTags(List<Object> tags) {
        this.tags = tags;
        return this;
    }

    public BlogPost withFeaturedImageSrc(FeaturedImageSrc featuredImageSrc) {
        this.featuredImageSrc = featuredImageSrc;
        return this;
    }

    public BlogPost withLinks(Links links) {
        this.links = links;
        return this;
    }
    public class Content {

        @SerializedName("rendered")
        @Expose
        public String rendered;
        @SerializedName("protected")
        @Expose
        public boolean _protected;

        public Content withRendered(String rendered) {
            this.rendered = rendered;
            return this;
        }

        public Content withProtected(boolean _protected) {
            this._protected = _protected;
            return this;
        }

    }

    public class Cury {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("href")
        @Expose
        public String href;
        @SerializedName("templated")
        @Expose
        public boolean templated;

        public Cury withName(String name) {
            this.name = name;
            return this;
        }

        public Cury withHref(String href) {
            this.href = href;
            return this;
        }

        public Cury withTemplated(boolean templated) {
            this.templated = templated;
            return this;
        }

    }

    public class Excerpt {

        @SerializedName("rendered")
        @Expose
        public String rendered;
        @SerializedName("protected")
        @Expose
        public boolean _protected;

        public Excerpt withRendered(String rendered) {
            this.rendered = rendered;
            return this;
        }

        public Excerpt withProtected(boolean _protected) {
            this._protected = _protected;
            return this;
        }

    }

    public class FeaturedImageSrc {

        @SerializedName("thumbnail")
        @Expose
        public String thumbnail;
        @SerializedName("medium")
        @Expose
        public String medium;
        @SerializedName("medium_large")
        @Expose
        public String mediumLarge;
        @SerializedName("large")
        @Expose
        public String large;

        public FeaturedImageSrc withThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public FeaturedImageSrc withMedium(String medium) {
            this.medium = medium;
            return this;
        }

        public FeaturedImageSrc withMediumLarge(String mediumLarge) {
            this.mediumLarge = mediumLarge;
            return this;
        }

        public FeaturedImageSrc withLarge(String large) {
            this.large = large;
            return this;
        }

    }

    public class Guid {

        @SerializedName("rendered")
        @Expose
        public String rendered;

        public Guid withRendered(String rendered) {
            this.rendered = rendered;
            return this;
        }

    }


    public class Links {

        @SerializedName("self")
        @Expose
        public List<Self> self = null;
        @SerializedName("collection")
        @Expose
        public List<Collection> collection = null;
        @SerializedName("about")
        @Expose
        public List<About> about = null;
        @SerializedName("author")
        @Expose
        public List<Author> author = null;
        @SerializedName("replies")
        @Expose
        public List<Reply> replies = null;
        @SerializedName("version-history")
        @Expose
        public List<VersionHistory> versionHistory = null;
        @SerializedName("predecessor-version")
        @Expose
        public List<PredecessorVersion> predecessorVersion = null;
        @SerializedName("wp:featuredmedia")
        @Expose
        public List<WpFeaturedmedium> wpFeaturedmedia = null;
        @SerializedName("wp:attachment")
        @Expose
        public List<WpAttachment> wpAttachment = null;
        @SerializedName("wp:term")
        @Expose
        public List<WpTerm> wpTerm = null;
        @SerializedName("curies")
        @Expose
        public List<Cury> curies = null;

        public Links withSelf(List<Self> self) {
            this.self = self;
            return this;
        }

        public Links withCollection(List<Collection> collection) {
            this.collection = collection;
            return this;
        }

        public Links withAbout(List<About> about) {
            this.about = about;
            return this;
        }

        public Links withAuthor(List<Author> author) {
            this.author = author;
            return this;
        }

        public Links withReplies(List<Reply> replies) {
            this.replies = replies;
            return this;
        }

        public Links withVersionHistory(List<VersionHistory> versionHistory) {
            this.versionHistory = versionHistory;
            return this;
        }

        public Links withPredecessorVersion(List<PredecessorVersion> predecessorVersion) {
            this.predecessorVersion = predecessorVersion;
            return this;
        }

        public Links withWpFeaturedmedia(List<WpFeaturedmedium> wpFeaturedmedia) {
            this.wpFeaturedmedia = wpFeaturedmedia;
            return this;
        }

        public Links withWpAttachment(List<WpAttachment> wpAttachment) {
            this.wpAttachment = wpAttachment;
            return this;
        }

        public Links withWpTerm(List<WpTerm> wpTerm) {
            this.wpTerm = wpTerm;
            return this;
        }

        public Links withCuries(List<Cury> curies) {
            this.curies = curies;
            return this;
        }

    }

    public class PredecessorVersion {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("href")
        @Expose
        public String href;

        public PredecessorVersion withId(int id) {
            this.id = id;
            return this;
        }

        public PredecessorVersion withHref(String href) {
            this.href = href;
            return this;
        }

    }


    public class Reply {

        @SerializedName("embeddable")
        @Expose
        public boolean embeddable;
        @SerializedName("href")
        @Expose
        public String href;

        public Reply withEmbeddable(boolean embeddable) {
            this.embeddable = embeddable;
            return this;
        }

        public Reply withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class Self {

        @SerializedName("href")
        @Expose
        public String href;

        public Self withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class Title {

        @SerializedName("rendered")
        @Expose
        public String rendered;

        public Title withRendered(String rendered) {
            this.rendered = rendered;
            return this;
        }

    }


    public class VersionHistory {

        @SerializedName("count")
        @Expose
        public int count;
        @SerializedName("href")
        @Expose
        public String href;

        public VersionHistory withCount(int count) {
            this.count = count;
            return this;
        }

        public VersionHistory withHref(String href) {
            this.href = href;
            return this;
        }

    }


    public class WpAttachment {

        @SerializedName("href")
        @Expose
        public String href;

        public WpAttachment withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class WpFeaturedmedium {

        @SerializedName("embeddable")
        @Expose
        public boolean embeddable;
        @SerializedName("href")
        @Expose
        public String href;

        public WpFeaturedmedium withEmbeddable(boolean embeddable) {
            this.embeddable = embeddable;
            return this;
        }

        public WpFeaturedmedium withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class WpTerm {

        @SerializedName("taxonomy")
        @Expose
        public String taxonomy;
        @SerializedName("embeddable")
        @Expose
        public boolean embeddable;
        @SerializedName("href")
        @Expose
        public String href;

        public WpTerm withTaxonomy(String taxonomy) {
            this.taxonomy = taxonomy;
            return this;
        }

        public WpTerm withEmbeddable(boolean embeddable) {
            this.embeddable = embeddable;
            return this;
        }

        public WpTerm withHref(String href) {
            this.href = href;
            return this;
        }

    }




    public class Collection {

        @SerializedName("href")
        @Expose
        public String href;

        public Collection withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class About {

        @SerializedName("href")
        @Expose
        public String href;

        public About withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class Author {

        @SerializedName("embeddable")
        @Expose
        public boolean embeddable;
        @SerializedName("href")
        @Expose
        public String href;

        public Author withEmbeddable(boolean embeddable) {
            this.embeddable = embeddable;
            return this;
        }

        public Author withHref(String href) {
            this.href = href;
            return this;
        }

    }
}
