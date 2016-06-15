var gulp = require('gulp');
var gp_uglify = require('gulp-uglify');
var gp_concat = require('gulp-concat');
var gp_rename = require('gulp-rename');
var bower = require('gulp-bower');
var mainBowerFiles = require('gulp-main-bower-files');
var merge = require('merge-stream');
var gulpFilter = require('gulp-filter');
var gulpConnect = require('gulp-connect');


gulp.task('iconFont', function(){
    return gulp.src(['./src/main/webapp/resources/components/bootstrap/dist/fonts/*'])
        .pipe(gulp.dest('./src/main/webapp/resources/build/fonts'));
});

gulp.task('bowerFiles', function() {
    var bw = gulp.src('bower.json')
        .pipe(mainBowerFiles({
            overrides: {
                bootstrap: {
                    main: [
                        './dist/js/bootstrap.js',
                        './dist/css/*',
                        './dist/fonts/*.*'
                    ]
                }
            }
        }));

    //var appFilesJS = gulp.src(['./src/main/webapp/resources/js/**/*.js']);
    //var appFilesCSS = gulp.src(['./src/main/webapp/resources/css/**/*.css']);


    //var jsFilter = gulpFilter(['**/*.js', '!**/*.min.js'], {restore: true});
    //var cssFilter = gulpFilter(['**/*.css', '!**/*.min.css'], {restore: true});

    var appFilesJS =  gulp.src(['./src/main/webapp/resources/components/jquery/dist/jquery.min.js',
        './src/main/webapp/resources/components/bootstrap/dist/js/bootstrap.min.js',
        './src/main/webapp/resources/components/underscore/underscore-min.js',
        './src/main/webapp/resources/components/angular/angular.min.js',
        './src/main/webapp/resources/components/angular-ui-router/release/angular-ui-router.min.js',
        './src/main/webapp/resources/components/angular-bootstrap/ui-bootstrap-tpls.js',
        './src/main/webapp/resources/components/angular-block-ui/dist/angular-block-ui.min.js',
        './src/main/webapp/resources/components/angular-breadcrumb/release/angular-breadcrumb.min.js',
        './src/main/webapp/resources/components/angularjs-toaster/toaster.min.js',
        './src/main/webapp/resources/js/**/*.js']);

    appFilesJS.pipe(gulp.dest('./src/main/webapp/resources/listJS'))
        .pipe(gp_concat('concat.js'))
        .pipe(gulp.dest('./src/main/webapp/resources/build/js'))
        .pipe(gp_rename('uglify.js'))
        .pipe(gp_uglify())
        .pipe(gulp.dest('./src/main/webapp/resources/build/js'));


    var appFilesCSS = gulp.src(['./src/main/webapp/resources/css/main.css',
        './src/main/webapp/resources/components/bootstrap/dist/css/bootstrap.min.css',
        './src/main/webapp/resources/components/angular-block-ui/dist/angular-block-ui.min.css',
        './src/main/webapp/resources/components/angularjs-toaster/toaster.min.css']);

    appFilesCSS.pipe(gulp.dest('./src/main/webapp/resources/listCSS'))
        .pipe(gp_concat('index.css'))
        .pipe(gulp.dest('./src/main/webapp/resources/build/css'));

    //return  merge(bw, appFilesJS, appFilesCSS)
    //
    //    .pipe(jsFilter)
    //    .pipe(gulp.dest('./src/main/webapp/resources/listJS'))
    //    .pipe(gp_concat('concat.js'))
    //    .pipe(gulp.dest('./src/main/webapp/resources/build/js'))
    //    .pipe(gp_rename('uglify.js'))
    //    .pipe(gp_uglify())
    //    .pipe(gulp.dest('./src/main/webapp/resources/build/js'))
    //    .pipe(jsFilter.restore)
    //    .pipe(cssFilter)
    //    .pipe(gulp.dest('./src/main/webapp/resources/listCSS'))
    //    .pipe(gp_concat('index.css'))
    //    .pipe(gulp.dest('./src/main/webapp/resources/build/css'))
    //    .pipe(gulpConnect.reload());

});

gulp.task('default', function() {
    gulp.run('bowerFiles');
    gulp.run('iconFont');

});

gulp.task('watch', function() {
    gulp.watch('./webapp/resources/js/**', function(event) {
        gulp.run('bowerFiles');

    });
});

